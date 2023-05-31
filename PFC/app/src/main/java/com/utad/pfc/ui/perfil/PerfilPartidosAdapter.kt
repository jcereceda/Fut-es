package com.utad.pfc.ui.perfil

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
import com.utad.pfc.model.Partido


class PerfilPartidosAdapter () : RecyclerView.Adapter<PerfilPartidosAdapter.ViewHolder>() {

    lateinit var miDataSet: List<Partido>
    lateinit var listEquipos: List<Equipo>
    inner class ViewHolder (var view: View):RecyclerView.ViewHolder(view) {
        var tvLocal = view.findViewById<TextView>(R.id.nombreEquipoLocal)
        var tvVisitante = view.findViewById<TextView>(R.id.nombreEquipoVisitante)
        var escudoLocal = view.findViewById<ImageView>(R.id.imgEscudoLocal)
        var escudoVisitante = view.findViewById<ImageView>(R.id.imgEscudoVisitante)

        var golesLocal = view.findViewById<TextView>(R.id.golesLocal)
        var golesVisitante = view.findViewById<TextView>(R.id.golesVisitante)


        fun bindItems(partido: Partido, local: Equipo, visitante: Equipo) {
            tvLocal.text = local.nombre_abrev
            tvVisitante.text = visitante.nombre_abrev
            golesLocal.text = partido.goles_local.toString()
            golesVisitante.text = partido.goles_visitante.toString()
            ponerEscudo(local.escudo, escudoLocal)
            ponerEscudo(visitante.escudo, escudoVisitante)
        }

        private fun ponerEscudo(escudo: String, Imgescudo: ImageView) {
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val imageRef = storageRef.child("equipos/" + escudo);

            imageRef.downloadUrl.addOnSuccessListener { uri ->
                Picasso.get().load(uri.toString()).into(Imgescudo)
            }.addOnFailureListener { exception ->
                Log.e("ListadoPartidosAdapter",exception.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_equipo_fav, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return miDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = miDataSet.get(position)
        val idLocal = data.id_equipo_local
        val idVisitante = data.id_equipo_visitante

        var equipoLocal: Equipo = listEquipos.filter { it.id == idLocal }.single()
        var equipoVisitante: Equipo = listEquipos.filter { it.id == idVisitante }.single()

        holder.bindItems(data, equipoLocal, equipoVisitante)

    }
}