package com.utad.pfc.ui.partidos

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.utad.pfc.API.ApiRest
import com.utad.pfc.R
import com.utad.pfc.databinding.FragmentListadoPartidosBinding
import com.utad.pfc.model.Equipo
import com.utad.pfc.model.Partido
import com.utad.pfc.ui.partidos.datos_partido.DatosPartido
import com.utad.pfc.ui.partidos.nuevo_partido.NuevoPartido
import kotlinx.coroutines.launch


class Listado_partidos : Fragment() {

    private var _binding: FragmentListadoPartidosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentListadoPartidosBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private val viewModel: PartidosViewModel by activityViewModels()
    private var equipos = arrayListOf<Equipo>()
    private var partidos = arrayListOf<Partido>()

    private lateinit var pbLoading: View
    private lateinit var rvPartidos: RecyclerView
    private var adapter: ListadoPartidosAdapter? = null
    private lateinit var refresh: SwipeRefreshLayout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPartidos = binding.listadoPartidos
        pbLoading = binding.progreso
        refresh = binding.refreshListPartidos

        if (ApiRest.UserLogged.rol == "creador") {
            binding.btnNuevoPartido.isVisible = true
        } else {
            binding.btnNuevoPartido.isVisible = false
        }

        listenEvents()
        initList()
        viewModel.getEquipos()
        viewModel.getPartidos()

        binding.aplicarFiltro.setOnClickListener {
            if (binding.tfFiltro.text.toString() != "") {
                var equipo: Equipo? = equipos.find {
                    (it.nombre_abrev).equals(binding.tfFiltro.text.toString(), true) ||
                            (it.nombre).equals(binding.tfFiltro.text.toString(), true) ||
                            it.nombre_abrev.contains(binding.tfFiltro.text.toString(), true)
                }

                if (equipo != null) {
                    viewModel.getPartidosEquipo(equipo.id)
                } else {
                    equipo = Equipo(0, "", "", "", "", "", "")
                    viewModel.getPartidosEquipo(equipo.id)
                }
            } else {
                viewModel.getPartidos()
                adapter?.notifyDataSetChanged()
            }

            cerrarTeclado()
        }

        binding.btnNuevoPartido.setOnClickListener {

            val fragment = NuevoPartido()
            var bundle = Bundle()
            bundle.putSerializable("equipos", equipos)
            fragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.main_container, fragment , "nuevo")
                ?.addToBackStack("listado")
                ?.commit()
        }

        refresh.setOnRefreshListener {
            viewModel.getPartidos()
        }

    }

    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.equipos.collect {
                        equipos = it
                        adapter?.listEquipos = it
                        adapter?.notifyDataSetChanged()
                    }
                }
                launch {
                    viewModel.partidos.collect {
                        adapter?.miDataSet = it
                        adapter?.notifyDataSetChanged()
                        refresh.isRefreshing = false
                    }
                }
                launch {
                    viewModel.loading.collect {
                        pbLoading.isVisible = it
                    }
                }
            }
        }
    }

    private fun initList() {
        val miManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        rvPartidos.layoutManager = miManager
        adapter = ListadoPartidosAdapter(requireContext(),partidos, equipos) {
            val partido = it
            val fragment = DatosPartido()
            val bundle = Bundle()
            bundle.putSerializable("partido",partido)
            bundle.putSerializable("local",equipos.filter { it.id == partido.id_equipo_local }.single())
            bundle.putSerializable("visitante",equipos.filter { it.id == partido.id_equipo_visitante }.single())
            fragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_container, fragment)?.addToBackStack(null)?.commit()

        }
        rvPartidos.adapter = adapter
    }

    private fun cerrarTeclado() {
        val input =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        input.hideSoftInputFromWindow(view?.getWindowToken(), 0)

    }
}