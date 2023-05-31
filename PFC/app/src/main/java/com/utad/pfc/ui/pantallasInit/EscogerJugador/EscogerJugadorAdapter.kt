package com.utad.pfc.ui.pantallasInit.EscogerJugador

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.utad.pfc.R
import com.utad.pfc.model.Equipo
import com.utad.pfc.model.Jugador


class EscogerJugadorAdapter ( val onClick: (Jugador) -> Unit) : RecyclerView.Adapter<EscogerJugadorAdapter.ViewHolder>() {
    lateinit var miDataSet: List<Jugador>

    inner class ViewHolder (var view: View):RecyclerView.ViewHolder(view) {
        var nombre = view.findViewById<TextView>(R.id.dialogNombreJugador)
        var imgJugador = view.findViewById<ImageView>(R.id.jugadorDialog)
        var posicion = view.findViewById<TextView>(R.id.posicion)
        var dorsal = view.findViewById<TextView>(R.id.dorsal)

        fun bindItems(data: Jugador) {
            nombre.text = data.apodo
            ponerEscudo(data.foto)
            posicion.text = data.posicion
            dorsal.text = data.dorsal.toString()
        }

        private fun ponerEscudo(escudo: String) {
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val imageRef = storageRef.child("jugadores/" + escudo);

            imageRef.downloadUrl.addOnSuccessListener { uri ->
                Picasso.get().load(uri.toString()).into(imgJugador)
            }.addOnFailureListener { exception ->
                Log.e("EscogeEquipoAdapter",exception.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dialog_jugadores, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return miDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = miDataSet.get(position)
        var elemento = holder.itemView.findViewById<View>(R.id.itemJugadores)
        elemento.setOnClickListener {
            onClick(data)
        }
        holder.bindItems(data)
    }

}