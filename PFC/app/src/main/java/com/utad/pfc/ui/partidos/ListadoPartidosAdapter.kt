package com.utad.pfc.ui.partidos

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import com.utad.pfc.R
import com.utad.pfc.model.Equipo
import com.utad.pfc.model.Partido

class ListadoPartidosAdapter(
    var context: Context,
    var miDataSet: List<Partido>,
    var listEquipos: List<Equipo>,
    val onClick: (Partido) -> Unit
) : RecyclerView.Adapter<ListadoPartidosAdapter.ViewHolder>() {

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var tvLocal = view.findViewById<TextView>(R.id.local)
        var tvVisitante = view.findViewById<TextView>(R.id.visitante)
        var escudoLocal = view.findViewById<ImageView>(R.id.escudoLocal)
        var escudoVisitante = view.findViewById<ImageView>(R.id.escudoVisitante)
        var directo = view.findViewById<ImageView>(R.id.gifDirecto)
        var fecha = view.findViewById<TextView>(R.id.tvFecha)

        fun bindItems(local: Equipo, visitante: Equipo, data: Partido) {
            tvLocal.text = local.nombre_abrev
            tvVisitante.text = visitante.nombre_abrev
            ponerEscudo(local.escudo, escudoLocal)
            ponerEscudo(visitante.escudo, escudoVisitante)
            fecha.text = data.fecha;
            if(!data.terminado){
                directo.isVisible = true
                Glide.with(context)
                    .asGif()
                    .load(R.drawable.endirecto)
                    .into(directo)
            } else {
                directo.isVisible = false
            }
        }

        private fun ponerEscudo(escudo: String, Imgescudo: ImageView) {
            val requestOptions = RequestOptions().apply {
                // Habilitar la caché de Glide en memoria y en disco
                diskCacheStrategy(DiskCacheStrategy.ALL)
                skipMemoryCache(false)
            }

            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val imageRef = storageRef.child("equipos/" + escudo);

            imageRef.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(context)
                    .load(uri)
                    .apply(requestOptions)
                    .into(Imgescudo)
            }.addOnFailureListener { exception ->
                Log.e("ListadoPartidosAdapter", exception.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_listado_partidos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return miDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = miDataSet.get(position)

        val idLocal = data.id_equipo_local
        val idVisitante = data.id_equipo_visitante

        var equipoLocal: Equipo = listEquipos.filter { it.id == idLocal }.single()
        var equipoVisitante: Equipo = listEquipos.filter { it.id == idVisitante }.single()

        var elemento = holder.itemView.findViewById<View>(R.id.card)
        elemento.setOnClickListener {
            onClick(data)
        }
        holder.bindItems(equipoLocal, equipoVisitante, data)
    }

}