package com.utad.pfc.ui.pantallasInit.EscogerJugador

import android.content.Intent
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
import com.utad.pfc.model.Equipo
import com.utad.pfc.ui.MainActivity
import com.utad.pfc.ui.pantallasInit.EscogerEquipo.EscogerEquipoAdapter
import com.utad.pfc.ui.pantallasInit.Iniciacion
import com.utad.pfc.ui.pantallasInit.IniciacionViewModel
import com.utad.pfc.ui.partidos.PartidosViewModel
import kotlinx.coroutines.launch

class EscogerJugador : Fragment() {

    private lateinit var equipo:Equipo
    private val viewModel: IniciacionViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        equipo = arguments?.getSerializable("equipo") as Equipo
        listenEvents()
        viewModel.getJugadoresEquipo(equipo.id)
        return inflater.inflate(R.layout.fragment_escoger_jugador, container, false)
    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.jugadores.collect {
                        adapter?.miDataSet = it
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private var adapter: EscogerJugadorAdapter? = null
    private lateinit var rvJugadores: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvJugadores = view.findViewById(R.id.rvEsogerJugador)
        initList()

    }
    private fun initList() {
        val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rvJugadores.layoutManager = miManager
        adapter = EscogerJugadorAdapter {
            viewModel.putJugadorFav(it)
            val intent = Intent(requireContext(), MainActivity::class.java)
            requireContext().startActivity(intent)
            activity?.finish()
        }

        rvJugadores.adapter = adapter
    }
}