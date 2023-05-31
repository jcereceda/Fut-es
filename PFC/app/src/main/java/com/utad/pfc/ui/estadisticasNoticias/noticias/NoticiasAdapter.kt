import android.icu.text.SimpleDateFormat
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
import com.utad.pfc.model.Noticia
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class NoticiasAdapter(
   val onClick: (Noticia) -> Unit
) :
    RecyclerView.Adapter<NoticiasAdapter.ViewHolder>() {

    lateinit var listNoticias: List<Noticia>

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val img = view.findViewById<ImageView>(R.id.imgNoticia)
        val tvTitular = view.findViewById<TextView>(R.id.titular)
        val fecha = view.findViewById<TextView>(R.id.fecha)

        fun bindItems(data: Noticia) {
            val fechaString = data.fecha
            val formatoEntrada = SimpleDateFormat("yyyy-MM-dd")
            val deit = formatoEntrada.parse(fechaString)

            val formatoSalida = SimpleDateFormat("dd/MM")
            val fechaConvertida = formatoSalida.format(deit)

            fecha.text = fechaConvertida
            tvTitular.text = data.titular
            ponerImagen(data.foto)
        }

        private fun ponerImagen(foto: String) {
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val imageRef = storageRef.child("noticias/" + foto);

            imageRef.downloadUrl.addOnSuccessListener { uri ->
                Picasso.get().load(uri.toString()).placeholder(R.drawable.ic_perfil_gris).into(img)
            }.addOnFailureListener { exception ->
                Log.e("NoticiasAdapter", exception.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticias, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNoticias.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listNoticias.get(position)

        holder.bindItems(data)

        var elemento = holder.itemView.findViewById<View>(R.id.cardNoticia)
        elemento.setOnClickListener {
            onClick(data)
        }
    }
}