package com.utad.pfc.ui.partidos.completar_partido

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.utad.pfc.R
import com.utad.pfc.model.Jugador
import de.hdodenhof.circleimageview.CircleImageView

class DialogJugadoresAdapter(context: Context, private val items: java.util.ArrayList<Jugador>) :
    ArrayAdapter<Jugador>(context, R.layout.item_dialog_jugadores, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_dialog_jugadores, parent, false)

        val item = items.get(position)

        val jugador = itemView.findViewById<TextView>(R.id.dialogNombreJugador)
        val posicion = itemView.findViewById<TextView>(R.id.posicion)
        val cara = itemView.findViewById<CircleImageView>(R.id.jugadorDialog)
        val dorsal = itemView.findViewById<TextView>(R.id.dorsal)
        dorsal.text = item.dorsal.toString()
        jugador.text = item.apodo
        posicion.text = item.posicion
        ponerEscudo(item.foto, cara)
        return itemView
    }

    private fun ponerEscudo(escudo: String, Imgescudo: CircleImageView) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val imageRef = storageRef.child("jugadores").child(escudo)

        imageRef.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(context)
                .load(uri)
                .placeholder(R.drawable.ic_perfil_gris)
                .error(R.drawable.ic_perfil_gris)
                .into(Imgescudo)
        }.addOnFailureListener { exception ->
            Log.e("DialogJugadoresAdapter", exception.toString())
        }
    }


}