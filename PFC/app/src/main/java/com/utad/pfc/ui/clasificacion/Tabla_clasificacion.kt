package com.utad.pfc.ui.clasificacion

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
import com.utad.pfc.databinding.FragmentTablaClasificacionBinding
import com.utad.pfc.model.Clasificacion
import com.utad.pfc.model.Equipo
import com.utad.pfc.model.Liga
import com.utad.pfc.model.ResponseClasificacion
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class Tabla_clasificacion : Fragment() {

    private var _binding: FragmentTablaClasificacionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTablaClasificacionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private var adapter: ClasificacionAdapter? = null
    private val viewModel: ClasificacionViewModel by activityViewModels()
    private lateinit var ligaAMostrar: Liga
    private lateinit var rvClasificacion: RecyclerView
    private var equipos = arrayListOf<Equipo>()
    private var posiciones = arrayListOf<Clasificacion>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvClasificacion = binding.rvClasificacion

        initList()
        listenEvents()
        // Por ahora será siempre 1, si se añaden más equipos, datos, habrá un form para poner la liga que quieras que se muestre
        viewModel.getLiga(1)
        viewModel.getEquipos()
        viewModel.getGolesContraComoLocal()
        viewModel.getGolesContraComoVisitante()
        viewModel.getGolesFavorComoLocal()
        viewModel.getGolesFavorComoVisitante()
        viewModel.getPartidosGanadosLocal()
        viewModel.getPartidosGanadosVisitante()
        viewModel.getPartidosEmpatadosLocal()
        viewModel.getPartidosEmpatadosVisitante()
        viewModel.getPartidosPerdidosVisitante()
        viewModel.getPartidosPerdidosLocal()

    }


    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.equipos.collect {
                        equipos = it
                        adapter?.listEquipos = it
                    }
                }
                launch {
                    viewModel.liga.collect {
                        ligaAMostrar = it
                        binding.cabecera.text = ligaAMostrar.nombre
                        viewModel.getClasificacion(ligaAMostrar.id)
                    }
                }
                launch {
                    viewModel.posiciones.collect {
                        adapter?.posiciones = it
                        adapter?.notifyDataSetChanged()
                    }
                }
                launch {
                    viewModel.loading.collect {
                        binding.loading.isVisible = it
                    }
                }
                launch {
                    viewModel.golesContraComoLocal.collect {
                        adapter?.golesContraLocal = it
                    }
                }
                launch {
                    viewModel.golesContraComoVisitante.collect {
                        adapter?.golesContraVisitante = it
                    }
                }
                launch {
                    viewModel.golesFavorComoLocal.collect {
                        adapter?.golesFavorLocal = it
                        //golesContraVisitante = it
                    }
                }
                launch {
                    viewModel.golesFavorComoVisitante.collect {
                        adapter?.golesFavorVisitante = it
                        //golesContraVisitante = it
                    }
                }
                launch {
                    viewModel.partidosGanadosVisitante.collect {
                        adapter?.partidosGanadosVisitante = it
                    }
                }
                launch {
                    viewModel.partidosGanadosLocal.collect {
                        adapter?.partidosGanadosLocal = it
                    }
                }
                launch {
                    viewModel.partidosEmpatadosLocal.collect{
                        adapter?.partidosEmpatadosLocal = it
                    }
                }
                launch {
                    viewModel.partidosEmpatadosVisitante.collect{
                        adapter?.partidosEmpatadosVisitante = it
                    }
                }

                launch {
                    viewModel.partidosPerdidosLocal.collect{
                        adapter?.partidosPerdidosLocal = it
                    }
                }
                launch {
                    viewModel.partidosPerdidosVisitante.collect{
                        adapter?.partidosPerdidosVisitante = it
                    }
                }
            }
        }
    }

    private fun initList() {
        val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rvClasificacion.layoutManager = miManager
        adapter = ClasificacionAdapter(requireContext(),posiciones, equipos)
        rvClasificacion.adapter = adapter
    }
}