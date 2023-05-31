package com.utad.pfc.ui.estadisticasNoticias.noticias

import NoticiasAdapter
import NoticiasViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.utad.pfc.API.ApiRest
import com.utad.pfc.R
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.EstadisticasAdapter
import com.utad.pfc.ui.estadisticasNoticias.noticias.leer_noticia.LeerNoticia
import com.utad.pfc.ui.partidos.datos_partido.DatosPartido
import kotlinx.coroutines.launch

class Noticias : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_noticias, container, false)
    }


    lateinit var rvNoticias: RecyclerView
    private val viewModel: NoticiasViewModel by activityViewModels()
    private var adapter: NoticiasAdapter? = null
    private lateinit var progressBar: ProgressBar
    private lateinit var btnNuevaNoticia: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNuevaNoticia = view.findViewById(R.id.btnNuevaNoticia)
        if(ApiRest.UserLogged.rol != "periodista"){
            btnNuevaNoticia.isVisible = false
        } else {
            btnNuevaNoticia.isVisible = true
        }


        rvNoticias = view.findViewById<RecyclerView>(R.id.rvNoticias)
        progressBar = view.findViewById<ProgressBar>(R.id.progressBar3)
        initList()
        listenEvents()

        viewModel.getNoticias()
    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.noticias.collect {
                        adapter?.listNoticias = it
                        adapter?.notifyDataSetChanged()
                    }
                }
                launch {
                    viewModel.loading.collect {
                        progressBar.isVisible = it
                    }
                }

            }
        }
    }

    private fun initList() {
        val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rvNoticias.layoutManager = miManager
        adapter = NoticiasAdapter() {
            val noticia = it
            val fragment = LeerNoticia()
            val bundle = Bundle()
            bundle.putSerializable("noticia",noticia)
            fragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_container, fragment)?.addToBackStack("estadisticasNoticias")?.commit()
        }
        rvNoticias.adapter = adapter
    }
}