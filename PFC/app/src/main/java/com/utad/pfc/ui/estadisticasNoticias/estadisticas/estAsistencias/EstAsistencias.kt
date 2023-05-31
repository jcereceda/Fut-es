package com.utad.pfc.ui.estadisticasNoticias.estadisticas.estAsistencias

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utad.pfc.databinding.FragmentEstAsistenciasBinding
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.EstadisticasAdapter
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.EstadisticasViewModel
import kotlinx.coroutines.launch


class EstAsistencias : Fragment() {
    private var _binding: FragmentEstAsistenciasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEstAsistenciasBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private val viewModel: EstadisticasViewModel by activityViewModels()
    private var adapter: EstadisticasAdapter? = null
    private lateinit var rvAsistencias: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAsistencias = binding.rvAsistencias

        initList()
        listenEvents()
        viewModel.getJugadoresAsistencia()
        viewModel.getEstadisticasAsistencias()

    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.asistencias.collect {
                        adapter?.listEstadistica = it
                        adapter?.notifyDataSetChanged()
                    }
                }
                launch {
                    viewModel.jugadoresConAsistencias.collect{
                        adapter?.listJugadores = it
                        adapter?.notifyDataSetChanged()
                    }
                }
                launch {
                    viewModel.loading.collect {
                        binding.progressBarAsist.isVisible = it
                    }
                }

            }
        }
    }

    private fun initList() {
        val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rvAsistencias.layoutManager = miManager
        adapter = EstadisticasAdapter()
        rvAsistencias.adapter = adapter
    }
}