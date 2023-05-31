package com.utad.pfc.ui.partidos.datos_partido

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.utad.pfc.R
import com.utad.pfc.databinding.FragmentDatosPartidoBinding
import com.utad.pfc.model.Equipo
import com.utad.pfc.model.EventoJugador
import com.utad.pfc.model.Jugador
import com.utad.pfc.model.Partido
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class DatosPartido : Fragment() {

    private var _binding: FragmentDatosPartidoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var local: Equipo
    lateinit var visitante: Equipo
    lateinit var partido: Partido
    private var golesPartido = arrayListOf<EventoJugador>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDatosPartidoBinding.inflate(inflater, container, false)
        val view = binding.root
        partido = arguments?.getSerializable("partido") as Partido
        local = arguments?.getSerializable("local") as Equipo
        visitante = arguments?.getSerializable("visitante") as Equipo
        viewModel.getGolesPartido(partido.id)
        viewModel.getAmarillasPartido(partido.id)
        viewModel.getRojasPartido(partido.id)

        viewModel.getJugadoresLocal(local.id)
        viewModel.getJugadoresVisitante(visitante.id)
        return view
    }

    private val viewModel: DatosPartidosViewModel by activityViewModels()
    private var jugadoresLocal = arrayListOf<Jugador>()
    private var jugadoresVisitante = arrayListOf<Jugador>()

    private var recuentoGolesLocal = ""
    private var recuentoGolesVisitante = ""
    private var recuentoAmarillasLocal = ""
    private var recuentoAmarillasVisitante = ""
    private var recuentoRojasLocal = ""
    private var recuentoRojasVisitante = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recuentoGolesLocal.text = ""
        binding.recuentoGolesVisitante.text = ""
        binding.recuentoAmarillasLocal.text = ""
        binding.recuentoAmarillasVisitante.text = ""
        binding.recuentoRojasLocal.text = ""
        binding.recuentoRojasVisitante.text = ""
        golesPartido.removeAll(golesPartido)
        listenEvents()

        ponerEscudo(local.escudo, binding.escudoEquipoLocal)
        ponerEscudo(visitante.escudo, binding.escudoEquipoVisitante)
        binding.tvEquipoLocal.text = local.nombre_abrev
        binding.tvEquipoVisitante.text = visitante.nombre_abrev
        asignarDatosPartido(partido)
        binding.Estadio.text = local.estadio


        viewModel.getArbitro(partido.id_arbitro)


        if (partido.goles_local <= 0){
            binding.baloncitoLocal.isVisible = false
            binding.recuentoGolesLocal.isVisible = false
        }

        if (partido.goles_visitante <= 0) {
            binding.baloncitoVisitante.isVisible = false
            binding.recuentoGolesVisitante.isVisible = false
        }


        binding.toolBarDatos.setNavigationOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
        }

        binding.refreshPartido.setOnRefreshListener {
            binding.recuentoAmarillasLocal.text = ""
            binding.recuentoAmarillasVisitante.text = ""
            binding.recuentoRojasLocal.text = ""
            binding.recuentoRojasVisitante.text = ""
            viewModel.getAmarillasPartido(partido.id)
            viewModel.getGolesPartido(partido.id)
            viewModel.getRojasPartido(partido.id)
            viewModel.getPartido(partido.id)
        }

    }


    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.arbitro.collect {
                        binding.Colegiado.text = it.nombre_completo
                    }
                }
                launch {
                    viewModel.jugadoresLocal.collect {
                        jugadoresLocal = it
                    }
                }
                launch {
                    viewModel.jugadoresVisitante.collect {
                        jugadoresVisitante = it
                    }
                }
                launch {
                    viewModel.golesPartido.collect {
                        golesPartido = it
                        recuentoGolesVisitante = ""
                        recuentoGolesLocal = ""
                        asignarGoles(it)
                    }
                }
                launch {
                    viewModel.amarillasPartido.collect{
                        recuentoAmarillasVisitante = ""
                        recuentoAmarillasLocal = ""
                        asignarAmarillas(it)
                    }
                }
                launch {
                    viewModel.rojasPartido.collect{
                        recuentoRojasLocal = ""
                        recuentoRojasVisitante = ""
                        asignarRojas(it)
                    }
                }
                launch {
                    viewModel.partido.collect{
                        if(it.id != 0) {
                            asignarDatosPartido(it)
                        }
                        it.id = 0
                    }
                }
            }
        }
    }

    private fun asignarDatosPartido(partido: Partido) {
        binding.tvGolLocal.text = partido.goles_local.toString()
        binding.tvGolVisitante.text = partido.goles_visitante.toString()
        binding.tvPaseLocal.text = partido.pases_local.toString()
        binding.tvPaseVisitante.text = partido.pases_visitante.toString()
        binding.tvCornerLocal.text = partido.corners_local.toString()
        binding.tvCornerVisitante.text = partido.corners_visitante.toString()
        binding.btnFaltaLocal.text = partido.faltas_local.toString()
        binding.btnFaltaVisitante.text = partido.faltas_visitante.toString()
        binding.tvTiroLocal.text = partido.tiros_local.toString()
        binding.tvTiroVisitante.text = partido.tiros_visitante.toString()
        binding.tvPosesionLocal.text = partido.posesion_local.toString() + "%"
        binding.tvPosesionVisitante.text = partido.posesion_visitante.toString() + "%"
    }

    private fun asignarRojas(listRojas: ArrayList<EventoJugador>) {
        if (!jugadoresLocal.isEmpty() && !jugadoresVisitante.isEmpty()) {
            for (item in listRojas) {

                for (jugador in jugadoresLocal) {
                    if (item.id_jugador == jugador.id) {
                        recuentoRojasLocal += " `" + item.minuto + " " + jugador.apodo
                    }
                }
                for (jugador in jugadoresVisitante) {
                    if (item.id_jugador == jugador.id) {
                        recuentoRojasVisitante += " `" + item.minuto + " " + jugador.apodo
                    }
                }
            }
        }
        if(recuentoRojasLocal == ""){
            binding.tarjetitaRojaLocal.isVisible = false
        } else {
            binding.tarjetitaRojaLocal.isVisible = true
            binding.recuentoRojasLocal.text = recuentoRojasLocal
        }
        if(recuentoRojasVisitante == ""){
            binding.tarjetitaRojaVisitante.isVisible = false
        } else {
            binding.tarjetitaRojaVisitante.isVisible = true
            binding.recuentoRojasVisitante.text = recuentoRojasVisitante
        }
        binding.refreshPartido.isRefreshing = false
    }

    private fun asignarAmarillas(listAmarillas: ArrayList<EventoJugador>) {
        if (!jugadoresLocal.isEmpty() && !jugadoresVisitante.isEmpty()) {
            for (item in listAmarillas) {

                for (jugador in jugadoresLocal) {
                    if (item.id_jugador == jugador.id) {
                        recuentoAmarillasLocal += " `" + item.minuto + " " + jugador.apodo
                    }
                }
                for (jugador in jugadoresVisitante) {
                    if (item.id_jugador == jugador.id) {
                        recuentoAmarillasVisitante += " `" + item.minuto + " " + jugador.apodo
                    }
                }
            }
        }
        if(recuentoAmarillasLocal == ""){
            binding.tarjetitaAmarillaLocal.isVisible = false
        } else {
            binding.tarjetitaAmarillaLocal.isVisible = true
            binding.recuentoAmarillasLocal.text = recuentoAmarillasLocal
        }
        if(recuentoAmarillasVisitante == ""){
            binding.tarjetitaAmarillaVisitante.isVisible = false
        } else {
            binding.tarjetitaAmarillaVisitante.isVisible = true
            binding.recuentoAmarillasVisitante.text = recuentoAmarillasVisitante
        }
        binding.refreshPartido.isRefreshing = false
    }

    private fun asignarGoles(goles: ArrayList<EventoJugador>) {
        if (!jugadoresLocal.isEmpty() && !jugadoresVisitante.isEmpty()) {
            for (item in goles) {

                for (jugador in jugadoresLocal) {
                    if (item.id_jugador == jugador.id) {
                        recuentoGolesLocal += " `" + item.minuto + " " + jugador.apodo
                    }
                }
                for (jugador in jugadoresVisitante) {
                    if (item.id_jugador == jugador.id) {
                        recuentoGolesVisitante += " `" + item.minuto + " " + jugador.apodo
                    }
                }
            }
        }
        binding.recuentoGolesLocal.text = recuentoGolesLocal
        binding.recuentoGolesVisitante.text = recuentoGolesVisitante
        binding.refreshPartido.isRefreshing = false
    }

    private fun ponerEscudo(escudo: String, Imgescudo: ImageView) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val imageRef = storageRef.child("equipos").child(escudo)

        imageRef.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(requireContext())
                .load(uri)
                .into(Imgescudo)
        }.addOnFailureListener { exception ->
            Log.e("DatosPartido", exception.toString())
        }
    }


}