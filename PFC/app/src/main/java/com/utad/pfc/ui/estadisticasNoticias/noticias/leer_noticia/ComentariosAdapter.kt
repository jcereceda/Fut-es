package com.utad.pfc.ui.estadisticasNoticias.noticias.leer_noticia

import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.utad.pfc.R
import com.utad.pfc.model.Comentario
import com.utad.pfc.model.Noticia
import de.hdodenhof.circleimageview.CircleImageView

class ComentariosAdapter() :
    RecyclerView.Adapter<ComentariosAdapter.ViewHolder>() {

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<CircleImageView>(R.id.imgUsuario)
        val nombreUser = view.findViewById<TextView>(R.id.nombreUser)
        val comentario = view.findViewById<TextView>(R.id.tfComentario)
        val fecha = view.findViewById<TextView>(R.id.tvFechaComent)

        fun bindItems(data: Comentario) {
            nombreUser.text = data.nombre + " " + data.apellidos
            comentario.text = data.comentario

            val fechaString = data.fecha
            val formatoEntrada = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val deit = formatoEntrada.parse(fechaString)

            val formatoSalida = SimpleDateFormat("dd/MM   HH:mm")
            val fechaConvertida = formatoSalida.format(deit)

            fecha.text = fechaConvertida
            ponerImg(data.foto, img)

        }

        private fun ponerImg(foto: String, img: CircleImageView) {
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val imageRef = storageRef.child("users/" + foto);
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                Picasso.get().load(uri.toString()).placeholder(R.drawable.ic_perfil_gris)
                    .error(R.drawable.ic_perfil_gris).into(img)
            }.addOnFailureListener { exception ->
                Log.e("ComentariosAdapter", exception.toString())
            }
        }

    }

    lateinit var comentarios: List<Comentario>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComentariosAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comentarios, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = comentarios.get(position)
        holder.bindItems(data)
    }

    override fun getItemCount(): Int {
        return comentarios.size
    }

}