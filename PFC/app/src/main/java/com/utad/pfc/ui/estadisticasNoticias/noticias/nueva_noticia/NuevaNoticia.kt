package com.utad.pfc.ui.estadisticasNoticias.noticias.nueva_noticia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.google.firebase.storage.FirebaseStorage
import com.utad.pfc.API.ApiRest
import com.utad.pfc.databinding.FragmentNuevaNoticiaBinding
import com.utad.pfc.model.DatosLogin
import com.utad.pfc.model.Noticia
import com.utad.pfc.model.Usuario
import com.utad.pfc.ui.Menu
import com.utad.pfc.ui.pantallasInit.Iniciacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NuevaNoticia : Fragment() {
    private var _binding: FragmentNuevaNoticiaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNuevaNoticiaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubirFoto.isEnabled = false

        binding.btnGuardarNoticia.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.requireContext())
            builder.setMessage("¿Deseas guardar la noticia?")
            builder.setPositiveButton("Si") { dialog, which ->
                val noticia = Noticia()
                noticia.cuerpo = binding.tfCuerpo.text.toString()
                noticia.foto = binding.tfTitular.text.toString() + ".jpg"
                noticia.titular = binding.tfTitular.text.toString()
                noticia.id_periodista = ApiRest.UserLogged.id

                guardarNoticia(noticia)

            }
            builder.setNegativeButton("No", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        val pickmedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    // Guardar permanentemente
                    val storage = FirebaseStorage.getInstance()
                    val fileReference = storage.reference.child("noticias/" + binding.tfTitular.text.toString() + ".jpg")
                    val uploadTask = fileReference.putFile(uri)
                    binding.tvNombreFoto.text = binding.tfTitular.text.toString() + ".jpg"

                    Log.i("NuevaNoticia", uri.toString())
                } else {
                    Log.i("NuevaNoticia", uri.toString())
                }
            }

        binding.tfTitular.doAfterTextChanged {
            if(binding.tfTitular.text.toString() == ""){
                binding.btnSubirFoto.isEnabled = false
            } else {
                binding.btnSubirFoto.isEnabled = true
            }
        }

        // Esté activo cuando el titular exista
        binding.btnSubirFoto.setOnClickListener {
            pickmedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

    }

    private fun guardarNoticia(noticia: Noticia) {

        val call = ApiRest.service.guardarNoticia(ApiRest.UserLogged.accessToken,noticia)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                if (response.isSuccessful) {
                    activity?.supportFragmentManager?.popBackStack()
                    Toast.makeText(requireContext(), "Noticia Guardada", Toast.LENGTH_SHORT)

                } else {
                    Log.e("NuevaNoticia", response.errorBody()?.string() ?: "error")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("NuevaNoticia", "error")
            }

        })


    }
}