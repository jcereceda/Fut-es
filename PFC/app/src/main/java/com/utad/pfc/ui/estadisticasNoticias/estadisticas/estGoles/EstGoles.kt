package com.utad.pfc.ui.estadisticasNoticias.estadisticas.estGoles

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
import com.utad.pfc.databinding.FragmentEstGolesBinding
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.EstadisticasAdapter
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.EstadisticasViewModel
import kotlinx.coroutines.launch


class EstGoles : Fragment() {

    private var _binding: FragmentEstGolesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEstGolesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private val viewModel: EstadisticasViewModel by activityViewModels()
    private var adapter: EstadisticasAdapter ? = null
    private lateinit var rvGoles: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvGoles = binding.rvGoles

        initList()
        listenEvents()
        viewModel.getJugadoresGol()
        viewModel.getEstadisticasGoles()

    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.goles.collect {
                        adapter?.listEstadistica = it
                        adapter?.notifyDataSetChanged()
                    }
                }
                launch {
                    viewModel.jugadoresConGol.collect{
                        adapter?.listJugadores = it
                        adapter?.notifyDataSetChanged()
                    }
                }
                launch {
                    viewModel.loading.collect {
                        binding.progressBar.isVisible = it
                    }
                }

            }
        }
    }

    private fun initList() {
        val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rvGoles.layoutManager = miManager
        adapter = EstadisticasAdapter()
        rvGoles.adapter = adapter
    }
}