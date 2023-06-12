package com.utad.pfc.ui.partidos.nuevo_partido

import android.content.Intent
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.provider.Telephony.Mms.Part
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.utad.pfc.API.ApiRest
import com.utad.pfc.R
import com.utad.pfc.databinding.FragmentNuevoPartidoBinding
import com.utad.pfc.model.DatosLogin
import com.utad.pfc.model.Equipo
import com.utad.pfc.model.Partido
import com.utad.pfc.model.Usuario
import com.utad.pfc.ui.Menu
import com.utad.pfc.ui.partidos.PartidosViewModel
import com.utad.pfc.ui.partidos.completar_partido.CompletarPartido
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.stream.Collectors

class NuevoPartido : Fragment() {

    private var _binding: FragmentNuevoPartidoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNuevoPartidoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    var equipos = arrayListOf<Equipo>()

    var equipoLocal: Equipo? = null
    var equipoVisitante: Equipo? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load("https://upload.wikimedia.org/wikipedia/commons/b/bd/Logo_Primera_RFEF.png")
            .into(binding.logo)

        equipos = arguments?.getSerializable("equipos") as ArrayList<Equipo>

        val adapter = DialogAdapter(requireContext(), equipos)



        binding.toolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }


        binding.selectedLocal.setOnClickListener {
            this.context?.let {
                MaterialAlertDialogBuilder(it)
                    .setTitle("Escoge Local")
                    .setAdapter(adapter) { dialog, which ->
                        equipoLocal = equipos.get(which)
                        binding.etLocal.text = equipoLocal?.nombre_abrev
                        ponerEscudo(equipoLocal!!.escudo, binding.selectedLocal)

                    }
                    .show()
            }
        }

        binding.selectedVisitante.setOnClickListener {
            this.context?.let {
                MaterialAlertDialogBuilder(it)
                    .setTitle("Escoge Visitante")
                    .setAdapter(adapter) { dialog, which ->
                        equipoVisitante = equipos.get(which)
                        binding.etVisitante.text = equipoVisitante!!.nombre_abrev
                        ponerEscudo(equipoVisitante!!.escudo, binding.selectedVisitante)
                    }
                    .show()
            }
        }

        binding.empezar.setOnClickListener {
            if (!(equipoLocal == null || equipoVisitante == null)) {
                //listenEvents()
                var partido = Partido()
                partido.id_equipo_local = equipoLocal!!.id
                partido.id_equipo_visitante = equipoVisitante!!.id
                partido.id_creador = ApiRest.UserLogged.id
                guardarPartido(partido)
                binding.tvError.text = ""
            } else {
                binding.tvError.text = "Seleccione equipos"
            }
        }
    }

    private fun guardarPartido (partido: Partido)
    {
        val call = ApiRest.service.postPartido(ApiRest.UserLogged.accessToken, partido)

        call.enqueue(object : Callback<Partido> {
            override fun onResponse(call: Call<Partido>, response: Response<Partido>) {

                val partidoPost = response.body()
                if (response.isSuccessful && partidoPost != null) {
                    var fragment = CompletarPartido()
                    var bundle = Bundle()
                    bundle.putSerializable("partido", partidoPost)
                    bundle.putSerializable("local",equipoLocal)
                    bundle.putSerializable("visitante",equipoVisitante)
                    fragment.arguments = bundle
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.main_container, fragment, "completar")
                        ?.addToBackStack(null)
                        ?.commit()
                    binding.tvError.text = ""

                } else {
                    var error = response.errorBody()?.string()
                    // Dar formato
                    error = error?.substring(12)
                    error = error?.replace("\"}","")

                    binding.tvError.text = error
                }
            }

            override fun onFailure(call: Call<Partido>, t: Throwable) {
                binding.tvError.text = "No se puede iniciar"
            }
        })


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
}