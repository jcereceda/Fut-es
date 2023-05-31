package com.utad.pfc.ui.estadisticasNoticias.estadisticas.estRojas

import android.os.Bundle
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
import com.utad.pfc.databinding.FragmentEstRojasBinding
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.EstadisticasAdapter
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.EstadisticasViewModel
import kotlinx.coroutines.launch

class EstRojas : Fragment() {

    private var _binding: FragmentEstRojasBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEstRojasBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private val viewModel: EstadisticasViewModel by activityViewModels()
    private var adapter: EstadisticasAdapter? = null
    private lateinit var rvRojas: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvRojas = binding.rvRojas

        initList()
        listenEvents()
        viewModel.getJugadoresTarjetas()
        viewModel.getEstadisticasRojas()

    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.tarjetasRojas.collect {
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
                        binding.progressBarRojas.isVisible = it
                    }
                }

            }
        }
    }

    private fun initList() {
        val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rvRojas.layoutManager = miManager
        adapter = EstadisticasAdapter()
        rvRojas.adapter = adapter
    }


}