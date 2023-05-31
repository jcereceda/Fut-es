package com.utad.pfc.ui.estadisticasNoticias.estadisticas

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
import com.utad.pfc.model.Jugador
import com.utad.pfc.model.ResponseEstadisticas
import de.hdodenhof.circleimageview.CircleImageView


class EstadisticasAdapter() :
    RecyclerView.Adapter<EstadisticasAdapter.ViewHolder>() {

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val posicion = view.findViewById<TextView>(R.id.estadsPosicion)
        val imgFutbolsita = view.findViewById<CircleImageView>(R.id.fotoJugador)
        val nombreFutbolista = view.findViewById<TextView>(R.id.nombreJugador)
        val cantEstadistica = view.findViewById<TextView>(R.id.cantEstad)

        fun bindItems(data: ResponseEstadisticas, jugador: Jugador, i: Int) {
            posicion.text = i.toString()
            nombreFutbolista.text = jugador.apodo
            cantEstadistica.text = data.total.toString()
            ponerFoto(jugador.foto)

        }

        private fun ponerFoto(fotoJugador: String) {
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val imageRef = storageRef.child("jugadores/" + fotoJugador);

            imageRef.downloadUrl.addOnSuccessListener { uri ->
                Picasso.get().load(uri.toString()).placeholder(R.drawable.ic_perfil_gris).into(imgFutbolsita)
            }.addOnFailureListener { exception ->
                Log.e("EstadisticasAdapter", exception.toString())
            }
        }

    }


    lateinit var listEstadistica: List<ResponseEstadisticas>
    lateinit var listJugadores: List<Jugador>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estadisticas, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listEstadistica.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listEstadistica.get(position)
        val jugador = listJugadores.filter { it.id == data.id_jugador }.single()
        holder.bindItems(data, jugador, position + 1)

    }

}