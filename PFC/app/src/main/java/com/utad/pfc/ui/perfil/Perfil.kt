package com.utad.pfc.ui.perfil

import android.R.attr.button
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.utad.pfc.API.ApiRest
import com.utad.pfc.R
import com.utad.pfc.databinding.FragmentPerfilBinding
import com.utad.pfc.model.Equipo
import com.utad.pfc.model.Jugador
import com.utad.pfc.ui.MainActivity
import com.utad.pfc.ui.partidos.PartidosViewModel
import com.utad.pfc.ui.perfil.modificar_perfil.ModificarPerfi
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class Perfil : Fragment() {
    private var _binding: FragmentPerfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    private val viewModel: PartidosViewModel by activityViewModels()
    private val PerflviewModel: PerfilViewModel by activityViewModels()
    private lateinit var rvPartidos: RecyclerView
    private var adapter: PerfilPartidosAdapter? = null
    private var equipos = arrayListOf<Equipo>()
    private var plantilla = arrayListOf<Jugador>()
    private lateinit var jugador: Jugador

    private lateinit var escudo: ImageView

    private lateinit var caraJugador: CircleImageView
    private lateinit var nombreJugador: TextView
    private lateinit var tfCantGoles: TextView
    private lateinit var tfCantAsistencias: TextView
    private lateinit var tfCantAmarillas: TextView
    private lateinit var tfCantRojas: TextView
    private lateinit var imgPerfil: CircleImageView
    private lateinit var nombreUser: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PerflviewModel.getJugadoresEquipo(ApiRest.UserLogged.id_equipo_fav)
        nombreUser = binding.tfNombreUser
        nombreUser.text = ApiRest.UserLogged.nombre + " " + ApiRest.UserLogged.apellidos
        imgPerfil = binding.imgUser
        rvPartidos = view.findViewById(R.id.rvPartidosEquipo)
        escudo = view.findViewById<ImageView>(R.id.escudoEquipoFav)
        caraJugador = view.findViewById(R.id.estadsJugador)
        nombreJugador = view.findViewById(R.id.nombreJugadorFav)
        tfCantGoles = view.findViewById(R.id.cantGoles)
        tfCantAsistencias = view.findViewById(R.id.cantAsist)
        tfCantAmarillas = view.findViewById(R.id.cantAmarillas)
        tfCantRojas = view.findViewById(R.id.cantRojas)

        if (ApiRest.UserLogged.foto != "")
            ponerEscudo("users/" + ApiRest.UserLogged.foto, imgPerfil)

        val pickmedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    // Guardar permanentemente
                    val storage = FirebaseStorage.getInstance()
                    val fileReference = storage.reference.child("users/" + ApiRest.UserLogged.foto)
                    val uploadTask = fileReference.putFile(uri)

                    // No se cambia el nombre de la foro por lo que sigue manteneindose en BD, si existe una en firebase, se sustituye

                    Log.i("perfil", uri.toString())
                    imgPerfil.setImageURI(uri)
                } else {
                    Log.i("perfil", uri.toString())
                }
            }

        binding.btnEdit.setOnClickListener {
            pickmedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.editarPerfil.setOnClickListener { v -> openDialog() }



        initList()
        listenEvents()

        if (ApiRest.UserLogged.id_equipo_fav != 0) {
            viewModel.getEquipos()
            viewModel.getPartidosEquipo(ApiRest.UserLogged.id_equipo_fav)
        } else {
            binding.ultimosPartidos.isVisible = false
            escudo.isVisible = false
            binding.divider2.isVisible = false
            rvPartidos.isVisible = false
            binding.divider3.isVisible = false
        }


        if (ApiRest.UserLogged.id_jugador_fav != 0) {
            PerflviewModel.getJugador(ApiRest.UserLogged.id_jugador_fav)
            PerflviewModel.getCantGoles(ApiRest.UserLogged.id_jugador_fav)
            PerflviewModel.getCantAsistencias(ApiRest.UserLogged.id_jugador_fav)
            PerflviewModel.getCantAmarillas(ApiRest.UserLogged.id_jugador_fav)
            PerflviewModel.getCantRojas(ApiRest.UserLogged.id_jugador_fav)
        } else {
            caraJugador.isVisible = false
            nombreJugador.isVisible = false
            binding.divider4.isVisible = false
            binding.divider5.isVisible = false
            binding.divider7.isVisible = false
            binding.divider6.isVisible = false
            binding.divider8.isVisible = false
            binding.golesFav.isVisible = false
            binding.AsistFav.isVisible = false
            binding.RojasFav.isVisible = false
            binding.amarillasFav.isVisible = false
            tfCantAmarillas.isVisible = false
            tfCantAsistencias.isVisible = false
            tfCantGoles.isVisible = false
            tfCantRojas.isVisible = false
        }

        binding.cerrarSesion.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.requireContext())
            builder.setMessage("¿Deseas cerrar sesion?")

            builder.setPositiveButton("Si") { dialog, which ->

                val sharedPreferences =
                    requireContext().getSharedPreferences("UsrLogin", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("email", "")
                editor.putString("pass", "")
                editor.apply()

                val intent = Intent(requireContext(), MainActivity::class.java)
                requireContext().startActivity(intent)
                activity?.finish()
            }
            builder.setNegativeButton("No", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }

    private fun openDialog() {
        val dialog = ModificarPerfi()
        var bundle = Bundle()
        bundle.putSerializable("equipos",equipos)
        if(ApiRest.UserLogged.id_jugador_fav != 0){
            bundle.putSerializable("jugador", jugador)
        }
        bundle.putSerializable("plantilla", plantilla)
        dialog.arguments  = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_container, dialog, "Perfil")?.addToBackStack(null)
            ?.commit()
       // activity?.supportFragmentManager?.let { dialog.show(it ,"Modif") }
    }


    private fun ponerEscudo(escudo: String, Imgescudo: ImageView) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val imageRef = storageRef.child(escudo);

        imageRef.downloadUrl.addOnSuccessListener { uri ->
            Picasso.get().load(uri.toString()).into(Imgescudo)
        }.addOnFailureListener { exception ->
            Log.e("Perfil", exception.toString())
        }
    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                if (ApiRest.UserLogged.id_equipo_fav != 0) {
                    launch {
                        viewModel.equipos.collect {
                            equipos = it
                            adapter?.listEquipos = it
                            val equipoFav =
                                equipos.filter { it.id == ApiRest.UserLogged.id_equipo_fav }
                                    .single()
                            ponerEscudo("equipos/" + equipoFav.escudo, escudo)
                            adapter?.notifyDataSetChanged()
                        }
                    }
                    launch {
                        viewModel.partidos.collect {
                            adapter?.miDataSet = it
                            adapter?.notifyDataSetChanged()
                        }
                    }
                }

                if (ApiRest.UserLogged.id_jugador_fav != 0) {
                    launch {
                        PerflviewModel.jugador.collect {
                            jugador = it
                            nombreJugador.text = it.nombre
                            ponerEscudo("jugadores/" + it.foto, caraJugador)
                        }
                    }
                    launch {
                        PerflviewModel.cantGoles.collect {
                            tfCantGoles.text = it.total.toString()
                        }
                    }
                    launch {
                        PerflviewModel.cantAsistencias.collect {
                            tfCantAsistencias.text = it.total.toString()
                        }
                    }
                    launch {
                        PerflviewModel.cantAmarillas.collect {
                            tfCantAmarillas.text = it.total.toString()
                        }
                    }
                    launch {
                        PerflviewModel.cantRojas.collect {
                            tfCantRojas.text = it.total.toString()
                        }
                    }
                    launch {
                        PerflviewModel.plantilla.collect{
                            plantilla = it
                        }
                    }
                }
            }
        }
    }


    private fun initList() {
        if (ApiRest.UserLogged.id_equipo_fav != 0) {
            val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            rvPartidos.layoutManager = miManager
            adapter = PerfilPartidosAdapter()
            rvPartidos.adapter = adapter
        }
    }

}