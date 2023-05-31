package com.utad.pfc.ui.pantallasInit.EscogerEquipo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utad.pfc.R
import com.utad.pfc.model.Equipo
import com.utad.pfc.ui.pantallasInit.EscogerJugador.EscogerJugador
import com.utad.pfc.ui.pantallasInit.IniciacionViewModel
import com.utad.pfc.ui.partidos.PartidosViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class EscogerEquipo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_escoger_equipo, container, false)
    }

    private val viewModel: IniciacionViewModel by activityViewModels()
    private val viewModelEquipos: PartidosViewModel by activityViewModels()
    private var adapter: EscogerEquipoAdapter? = null
    private lateinit var rvEquipos: RecyclerView
    private lateinit var equipos: ArrayList<Equipo>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvEquipos = view.findViewById(R.id.rvEscogerEquipo)
        equipos =  arguments?.getSerializable("equipos") as ArrayList<Equipo>
        initList()
        viewModelEquipos.getEquipos()

    }

    private fun initList() {
        val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rvEquipos.layoutManager = miManager
        adapter = EscogerEquipoAdapter(equipos) {
            viewModel.putEquipoFav(it)
            val equipo = it
            val fragment = EscogerJugador()
            val bundle = Bundle()
            bundle.putSerializable("equipo", equipo)
            fragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.conteinerIniciacion, fragment)?.commit()
        }

        rvEquipos.adapter = adapter
    }

}