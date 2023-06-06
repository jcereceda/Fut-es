package com.utad.pfc.ui.estadisticasNoticias.noticias.nueva_noticia

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
import com.google.firebase.storage.FirebaseStorage
import com.utad.pfc.API.ApiRest
import com.utad.pfc.databinding.FragmentNuevaNoticiaBinding

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

        binding.btnGuardarNoticia.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.requireContext())
            builder.setMessage("¿Deseas guardar la noticia?")

            builder.setPositiveButton("Si") { dialog, which ->
                //viewModel.putNoticia(noticia)
                activity?.supportFragmentManager?.popBackStack()
                Toast.makeText(requireContext(), "Noticia Guardada", Toast.LENGTH_SHORT)
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
                    val fileReference = storage.reference.child("noticias/" + ApiRest.UserLogged.foto)
                    val uploadTask = fileReference.putFile(uri)

                    Log.i("NuevaNoticia", uri.toString())
                } else {
                    Log.i("NuevaNoticia", uri.toString())
                }
            }

        // Esté activo cuando el titular exista
        binding.btnSubirFoto.setOnClickListener {
            pickmedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

    }
}