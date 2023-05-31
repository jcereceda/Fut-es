package com.utad.pfc.ui.estadisticasNoticias.noticias.leer_noticia

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.firebase.storage.FirebaseStorage
import com.utad.pfc.API.ApiRest
import com.utad.pfc.R
import com.utad.pfc.model.Comentario
import com.utad.pfc.model.Noticia
import kotlinx.coroutines.launch


class LeerNoticia : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leer_noticia, container, false)
    }

    private val viewModel: LeerNoticiaViewModel by activityViewModels()

    lateinit var img: ImageView
    lateinit var noticia: Noticia
    lateinit var toolBar: MaterialToolbar

    private var adapter = ComentariosAdapter()

    private lateinit var rvComents: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noticia = arguments?.getSerializable("noticia") as Noticia
        img = view.findViewById(R.id.imgNoticia)
        ponerImg(noticia.foto)
        rvComents = view.findViewById(R.id.rvComentarios)

        val titular = view.findViewById<TextView>(R.id.titular)
        titular.text = noticia.titular
        val cuerpo = view.findViewById<TextView>(R.id.cuerpo)
        cuerpo.text = noticia.cuerpo

        toolBar = view.findViewById(R.id.toolbarNoticia)
        toolBar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        initList()
        listenEvents()
        viewModel.getComentarios(noticia.id)

        var tfComentario = view.findViewById<EditText>(R.id.tfCOment)
        var btnSubirComentario = view.findViewById<MaterialButton>(R.id.subirComent)
        btnSubirComentario.setOnClickListener {
            var comentario = Comentario()
            comentario.id_noticia = noticia.id
            comentario.comentario = tfComentario.text.toString()
            comentario.id_usuario = ApiRest.UserLogged.id
            viewModel.subirComentario(comentario)
            //viewModel.getComentarios(noticia.id)
            tfComentario.setText("")
            adapter.notifyDataSetChanged()
        }

    }

    private fun initList() {
        val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rvComents.layoutManager = miManager
        adapter = ComentariosAdapter()
        rvComents.adapter = adapter
    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.comentarios.collect {
                        adapter.comentarios = it
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun ponerImg(foto: String) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val imageRef = storageRef.child("noticias/" + foto);

        imageRef.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(requireContext())
                .load(uri)
                .into(img)
        }.addOnFailureListener { exception ->
            Log.e("NoticiasAdapter", exception.toString())
        }
    }
}