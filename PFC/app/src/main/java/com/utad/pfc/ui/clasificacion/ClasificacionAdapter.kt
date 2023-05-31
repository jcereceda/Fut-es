package com.utad.pfc.ui.clasificacion

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.utad.pfc.R
import com.utad.pfc.model.*


class ClasificacionAdapter(
    val context: Context,
    var posiciones: List<Clasificacion>,
    var listEquipos: List<Equipo>
) : RecyclerView.Adapter<ClasificacionAdapter.ViewHolder>() {

    lateinit var golesContraVisitante: List<ResponseClasificacion>
    lateinit var golesContraLocal: List<ResponseClasificacion>
    lateinit var golesFavorLocal: List<ResponseClasificacion>
    lateinit var golesFavorVisitante: List<ResponseClasificacion>
    lateinit var partidosGanadosLocal: List<ResponseClasificacion>
    lateinit var partidosGanadosVisitante: List<ResponseClasificacion>
    lateinit var partidosEmpatadosVisitante: List<ResponseClasificacion>
    lateinit var partidosEmpatadosLocal: List<ResponseClasificacion>
    lateinit var partidosPerdidosLocal: List<ResponseClasificacion>
    lateinit var partidosPerdidosVisitante: List<ResponseClasificacion>

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val imgEscudo = view.findViewById<ImageView>(R.id.escudoEquipo)
        val nombreEquipo = view.findViewById<TextView>(R.id.nombreEquipo)
        val pts = view.findViewById<TextView>(R.id.puntos)
        val posTabla = view.findViewById<TextView>(R.id.posicion)
        val tvgolesContra = view.findViewById<TextView>(R.id.golesContra)
        val tvgolesFavor = view.findViewById<TextView>(R.id.golesFavor)
        val tvPartidosGanados = view.findViewById<TextView>(R.id.partidosGanados)
        val tvPartidosEmpatados = view.findViewById<TextView>(R.id.partidosEmpatados)
        val tvPartidosPerdidos = view.findViewById<TextView>(R.id.tvPartidosPerdidos)
        val tvTotalPartidos = view.findViewById<TextView>(R.id.partidosJugados)

        fun bindItems(
            pos: Clasificacion,
            equipo: Equipo,
            position: Int,
            golesContra: Int,
            golesFavor: Int,
            partidosGanados: Int,
            partidosEmpatados: Int,
            partidosPerdidos: Int
        ) {
            ponerEscudo(equipo.escudo, imgEscudo)
            nombreEquipo.text = equipo.nombre_abrev
            pts.text = pos.puntos.toString()
            posTabla.text = position.toString()
            tvgolesContra.text = golesContra.toString()
            tvgolesFavor.text = golesFavor.toString()
            tvPartidosGanados.text = partidosGanados.toString()
            tvPartidosEmpatados.text = partidosEmpatados.toString()
            tvPartidosPerdidos.text = partidosPerdidos.toString()
            tvTotalPartidos.text =
                (partidosEmpatados + partidosPerdidos + partidosGanados).toString()
        }

        private fun ponerEscudo(escudo: String, Imgescudo: ImageView?) {
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
                    .into(Imgescudo!!)
            }.addOnFailureListener { exception ->
                Log.e("ClasificacionAdapter", exception.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tabla_liga, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posiciones.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = posiciones.get(position)
        val equipo = listEquipos.filter { data.id_equipo == it.id }.single()

        var golesContra = 0
        golesContra += golesContraLocal.find { equipo.id == it.id_equipo }?.total ?: 0
        golesContra += golesContraVisitante.find { equipo.id == it.id_equipo }?.total ?: 0

        var golesFavor = 0
        golesFavor += golesFavorLocal.find { equipo.id == it.id_equipo }?.total ?: 0
        golesFavor += golesFavorVisitante.find { equipo.id == it.id_equipo }?.total ?: 0

        var partidosGanados = 0
        partidosGanados += partidosGanadosLocal.find { equipo.id == it.id_equipo }?.total ?: 0
        partidosGanados += partidosGanadosVisitante.find { equipo.id == it.id_equipo }?.total ?: 0

        var partidosEmpatados = 0
        partidosEmpatados += partidosEmpatadosLocal.find { equipo.id == it.id_equipo }?.total ?: 0
        partidosEmpatados += partidosEmpatadosVisitante.find { equipo.id == it.id_equipo }?.total
            ?: 0

        var partidosPerdidos = 0
        partidosPerdidos += partidosPerdidosLocal.find { equipo.id == it.id_equipo }?.total ?: 0
        partidosPerdidos += partidosPerdidosVisitante.find { equipo.id == it.id_equipo }?.total
            ?: 0


        holder.bindItems(
            data,
            equipo,
            position + 1,
            golesContra,
            golesFavor,
            partidosGanados,
            partidosEmpatados,
            partidosPerdidos
        )
    }

}

