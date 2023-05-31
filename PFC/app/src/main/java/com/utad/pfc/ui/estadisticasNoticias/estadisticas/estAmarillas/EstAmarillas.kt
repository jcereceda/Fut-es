package com.utad.pfc.ui.estadisticasNoticias.estadisticas.estAmarillas

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
import com.utad.pfc.R
import com.utad.pfc.databinding.FragmentEstAmarillasBinding
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.EstadisticasAdapter
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.EstadisticasViewModel
import kotlinx.coroutines.launch


class EstAmarillas : Fragment() {

    private var _binding: FragmentEstAmarillasBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEstAmarillasBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private val viewModel: EstadisticasViewModel by activityViewModels()
    private var adapter: EstadisticasAdapter? = null
    private lateinit var rvAmarillas: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAmarillas = binding.rvAmarillas

        initList()
        listenEvents()
        viewModel.getJugadoresTarjetas()
        viewModel.getEstadisticasAmarillas()

    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.tarjetasAmarillas.collect {
                        adapter?.listEstadistica = it
                        adapter?.notifyDataSetChanged()
                    }
                }
                launch {
                    viewModel.jugadoresConTarjetas.collect{
                        adapter?.listJugadores = it
                        adapter?.notifyDataSetChanged()
                    }
                }
                launch {
                    viewModel.loading.collect {
                        binding.progressBarAmarillas.isVisible = it
                    }
                }

            }
        }
    }

    private fun initList() {
        val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rvAmarillas.layoutManager = miManager
        adapter = EstadisticasAdapter()
        rvAmarillas.adapter = adapter
    }

}