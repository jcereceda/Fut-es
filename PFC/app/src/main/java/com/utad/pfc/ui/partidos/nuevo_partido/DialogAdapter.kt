package com.utad.pfc.ui.partidos.nuevo_partido

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.utad.pfc.R
import com.utad.pfc.model.Equipo
import de.hdodenhof.circleimageview.CircleImageView

class DialogAdapter (context: Context, private val items: java.util.ArrayList<Equipo>) : ArrayAdapter<Equipo>(context, R.layout.item_dialog_equipos, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_dialog_equipos, parent, false)

        val item = items.get(position)

        val equipo = itemView.findViewById<TextView>(R.id.dialogNombre)
        val escudo = itemView.findViewById<ImageView>(R.id.escudoDialog)
        equipo.text = item.nombre_abrev
        ponerEscudo(item.escudo, escudo)
        return itemView
    }

    private fun ponerEscudo(escudo: String, Imgescudo: ImageView) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val imageRef = storageRef.child("equipos").child(escudo)

        imageRef.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(context)
                .load(uri)
                .into(Imgescudo)
        }.addOnFailureListener { exception ->
            Log.e("DialogAdapter",exception.toString())
        }
    }
}