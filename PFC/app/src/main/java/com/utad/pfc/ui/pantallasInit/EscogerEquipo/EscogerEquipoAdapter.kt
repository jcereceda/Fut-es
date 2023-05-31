package com.utad.pfc.ui.pantallasInit.EscogerEquipo

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


class EscogerEquipoAdapter (var miDataSet: List<Equipo>, val onClick: (Equipo) -> Unit) : RecyclerView.Adapter<EscogerEquipoAdapter.ViewHolder>() {

    inner class ViewHolder (var view: View):RecyclerView.ViewHolder(view)  {
        var nombre = view.findViewById<TextView>(R.id.dialogNombre)
        var imgEscudo = view.findViewById<ImageView>(R.id.escudoDialog)

        fun bindItems(data: Equipo) {
            nombre.text = data.nombre_abrev
            ponerEscudo(data.escudo)
        }

        private fun ponerEscudo(escudo: String) {
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val imageRef = storageRef.child("equipos/" + escudo);

            imageRef.downloadUrl.addOnSuccessListener { uri ->
                Picasso.get().load(uri.toString()).into(imgEscudo)
            }.addOnFailureListener { exception ->
                Log.e("EscogeEquipoAdapter",exception.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dialog_equipos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return miDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = miDataSet.get(position)
        var elemento = holder.itemView.findViewById<View>(R.id.itemEquipos)
        elemento.setOnClickListener {
            onClick(data)
        }
        holder.bindItems(data)
    }

}