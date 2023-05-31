package com.utad.pfc.ui.partidos.completar_partido

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.storage.FirebaseStorage
import com.utad.pfc.databinding.FragmentCompletarPartidoBinding
import com.utad.pfc.model.*
import com.utad.pfc.ui.partidos.nuevo_partido.DialogAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class CompletarPartido : Fragment() {
    private var _binding: FragmentCompletarPartidoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompletarPartidoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    lateinit var partido: Partido
    lateinit var equipoLocal: Equipo
    lateinit var equipoVisitante: Equipo
    var plantillaLocal: java.util.ArrayList<Jugador> = arrayListOf()
    var plantillaVisitante: java.util.ArrayList<Jugador> = arrayListOf()
    lateinit var adapterLocal: DialogJugadoresAdapter
    lateinit var adapterVisitante: DialogJugadoresAdapter
    private val viewModel: CompletarPartidoViewModel by activityViewModels()

    private lateinit var handler: Handler
    private var pos = false
    private var descanso = false
    private var recuentoGolesLocal = ""
    private var recuentoGolesVisitante = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        partido = arguments?.getSerializable("partido") as Partido
        equipoLocal = arguments?.getSerializable("local") as Equipo
        equipoVisitante = arguments?.getSerializable("visitante") as Equipo

        viewModel.getJugadoresLocal(equipoLocal.id)
        viewModel.getJugadoresVisitante(equipoVisitante.id)

        binding.tvEquipoLocal.text = equipoLocal.nombre_abrev
        binding.tvEquipoVisitante.text = equipoVisitante.nombre_abrev

        binding.tiempoDeJuego.start()

        binding.baloncitoLocal.isVisible = false
        binding.baloncitoVisitante.isVisible = false

        ponerEscudo(equipoLocal.escudo, binding.escudoEquipoLocal)
        ponerEscudo(equipoVisitante.escudo, binding.escudoEquipoVisitante)

        binding.Estadio.text = equipoLocal.estadio
        listenEvents()
        viewModel.getArbitro(partido.id_arbitro)


        handler = Handler(Looper.getMainLooper())

        // Poner Lo de la posesion bien
        sumarPosesion()
        binding.swPosesion.setOnCheckedChangeListener { _, checked ->
            when {
                checked -> {
                    pos = false
                }
                else -> {
                    pos = true
                }
            }

        }
        // FUncionalidad a terminar partido, goles ....
        binding.btnPaseLocal.setOnClickListener {
            viewModel.putEvento(partido.id, "paseLocal")
        }
        binding.btnPaseVisitante.setOnClickListener {
            viewModel.putEvento(partido.id, "paseVisitante")
        }
        binding.btnCornerLocal.setOnClickListener {
            viewModel.putEvento(partido.id, "cornerLocal")
        }
        binding.btnCornerVisitante.setOnClickListener {
            viewModel.putEvento(partido.id, "cornerVisitante")
        }
        binding.btnFaltaLocal.setOnClickListener {
            viewModel.putEvento(partido.id, "faltaLocal")
        }
        binding.btnFaltaVisitante.setOnClickListener {
            viewModel.putEvento(partido.id, "faltaVisitante")
        }
        binding.btnTiroLocal.setOnClickListener {
            viewModel.putEvento(partido.id, "tiroLocal")
        }
        binding.btnTiroVisitante.setOnClickListener {
            viewModel.putEvento(partido.id, "tiroVisitante")
        }

        binding.btnGolLocal.setOnClickListener {
            asignarGolYAstistenciaLocal()
        }

        binding.btnGolVisitante.setOnClickListener {
            asignarGolYAstistenciaVisitante()
        }

        binding.btnAmarillaLocal.setOnClickListener {
            asignarTarjeta("Amarilla", "local")
        }
        binding.btnAmarillaVisitante.setOnClickListener {
            asignarTarjeta("Amarilla", "visitante")
        }

        binding.btnRojaLocal.setOnClickListener {
            asignarTarjeta("Roja", "local")
        }
        binding.btnRojaVisitante.setOnClickListener {
            asignarTarjeta("Roja", "visitante")
        }


        binding.toolbarCompletar.setNavigationOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.requireContext())
            builder.setTitle("Cuidado!")
            builder.setMessage("Estas seguro que quieres salir? Perderás los datos no guardados")

            builder.setPositiveButton("Si") { dialog, which ->
                activity?.supportFragmentManager?.popBackStack()
            }
            builder.setNegativeButton("No", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        binding.btnDescanso.setOnClickListener {
            binding.tiempoDeJuego.stop()
            descanso = true
        }
        binding.btnReanudar.setOnClickListener {
            binding.tiempoDeJuego.start()
            descanso = false
        }

        binding.btnFinal.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.requireContext())
            builder.setMessage("¿Deseas terminar el partido?")

            builder.setPositiveButton("Si") { dialog, which ->
                binding.tiempoDeJuego.stop()
                viewModel.putEvento(partido.id, "terminar")
                activity?.supportFragmentManager?.popBackStack()
                Toast.makeText(requireContext(), "Partido Terminado", Toast.LENGTH_SHORT)
            }
            builder.setNegativeButton("No", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }

    private fun asignarGolYAstistenciaVisitante() {
        // Asignar el Gol a jugador y mostrar baloncito
        var evento = EventoJugador()
        evento.tipoEvento = "goles"
        val builder = MaterialAlertDialogBuilder(this.requireContext())
        builder.setTitle("Gol")
        builder.setAdapter(adapterVisitante) { dialog, which ->

            evento.id_jugador = plantillaVisitante.get(which).id
            evento.minuto =
                (binding.tiempoDeJuego.text.toString()).substring(0, 2).toInt()
            evento.id_partido = partido.id
            viewModel.postEventoJugador(evento)
            viewModel.putEvento(partido.id, "tiroVisitante")
            viewModel.putEvento(partido.id, "golVisitante")
            partido.goles_visitante++
            binding.tvGolVisitante.text = partido.goles_visitante.toString()
            // poner baloncito
            binding.baloncitoVisitante.isVisible = true
            recuentoGolesVisitante += if (recuentoGolesVisitante == "") " `" else ", `" + evento.minuto + " " + plantillaVisitante.get(
                which
            ).apodo

            binding.recuentoGolesVisitante.text = recuentoGolesVisitante

            var eventoAsist = EventoJugador()
            eventoAsist.tipoEvento = "asistencias"
            val builder = MaterialAlertDialogBuilder(this.requireContext())
            builder.setTitle("Asistencia")
            builder.setAdapter(adapterVisitante) { dialog, which ->
                // Coger jugador del equipo
                eventoAsist.id_jugador = plantillaVisitante.get(which).id
                // Coger minuto
                eventoAsist.minuto =
                    (binding.tiempoDeJuego.text.toString()).substring(0, 2).toInt()
                // partido id
                eventoAsist.id_partido = partido.id
                // Postear el gol
                viewModel.postEventoJugador(eventoAsist)
            }

            val dialog = builder.create()
            dialog.show()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun asignarGolYAstistenciaLocal() {
        // Asignar el Gol a jugador y mostrar baloncito
        var evento = EventoJugador()
        evento.tipoEvento = "goles"
        val builder = MaterialAlertDialogBuilder(this.requireContext())
        builder.setTitle("Gol")
        builder.setAdapter(adapterLocal) { dialog, which ->

            evento.id_jugador = (plantillaLocal).get(which).id
            evento.minuto =
                (binding.tiempoDeJuego.text.toString()).substring(0, 2).toInt()
            evento.id_partido = partido.id
            viewModel.postEventoJugador(evento)
            viewModel.putEvento(partido.id, "tiroLocal")
            viewModel.putEvento(partido.id, "golLocal")
            partido.goles_local++
            binding.tvGolLocal.text = partido.goles_local.toString()
            // poner baloncito
            binding.baloncitoLocal.isVisible = true
            recuentoGolesLocal += if (recuentoGolesLocal == "") " `" else ", `" + evento.minuto + " " + plantillaLocal.get(
                which
            ).apodo

            binding.recuentoGolesLocal.text = recuentoGolesLocal

            var eventoAsist = EventoJugador()
            eventoAsist.tipoEvento = "asistencias"
            val builder = MaterialAlertDialogBuilder(this.requireContext())
            builder.setTitle("Asistencia")
            builder.setAdapter(adapterLocal) { dialog, which ->
                // Coger jugador del equipo
                eventoAsist.id_jugador = (plantillaLocal).get(which).id
                // Coger minuto
                eventoAsist.minuto =
                    (binding.tiempoDeJuego.text.toString()).substring(0, 2).toInt()
                // partido id
                eventoAsist.id_partido = partido.id
                // Postear el gol
                viewModel.postEventoJugador(eventoAsist)
            }

            val dialog = builder.create()
            dialog.show()
        }

        val dialog = builder.create()
        dialog.show()

    }

    private fun asignarTarjeta(tipo: String, lado: String) {
        var evento = EventoJugador()
        evento.tipoEvento = "tarjetas"
        val builder = MaterialAlertDialogBuilder(this.requireContext())
        builder.setTitle(evento.tipoEvento)
        builder.setAdapter(if (lado == "local") adapterLocal else adapterVisitante) { dialog, which ->
            evento.id_jugador =
                (if (lado == "local") plantillaLocal else plantillaVisitante).get(which).id
            evento.minuto =
                (binding.tiempoDeJuego.text.toString()).substring(0, 2).toInt()
            evento.id_partido = partido.id
            evento.color = tipo
            viewModel.postEventoJugador(evento)
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun sumarPosesion() {

        handler.postDelayed(object : Runnable {
            override fun run() {
                // Incrementar el número y actualizar la interfaz de usuario
                if (pos && partido.posesion_local <= 100 && !descanso) {
                    partido.posesion_visitante--
                    partido.posesion_local++
                    Log.i(
                        "completarPartido",
                        "Posesion para local" + partido.posesion_local
                    )
                } else if (!pos && partido.posesion_visitante <= 100 && !descanso) {
                    partido.posesion_visitante++
                    partido.posesion_local--
                    Log.i(
                        "completarPartido",
                        "Posesion para visitante" + partido.posesion_visitante
                    )
                }
                viewModel.putPosesion(
                    partido.id,
                    Posesion(partido.posesion_local, partido.posesion_visitante)
                )

                // Volver a llamar a la tarea después de 5 segundos
                handler.postDelayed(this, 10000)
            }
        }, 10000)
    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.arbitro.collect {
                        binding.Colegiado.text = "Colegiado: " + it.nombre_completo
                    }
                }
                launch {
                    viewModel.plantillaLocal.collect {
                        plantillaLocal = it
                        adapterLocal = DialogJugadoresAdapter(requireContext(), it)

                    }
                }
                launch {
                    viewModel.plantillaVisitante.collect {
                        plantillaVisitante = it
                        adapterVisitante = DialogJugadoresAdapter(requireContext(), it)
                    }
                }
            }
        }
    }

    private fun ponerEscudo(escudo: String, Imgescudo: ImageView) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val imageRef = storageRef.child("equipos/" + escudo);

        imageRef.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(requireContext())
                .load(uri)
                .into(Imgescudo)
        }.addOnFailureListener { exception ->
            Log.e("NuevoPartido", exception.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

}