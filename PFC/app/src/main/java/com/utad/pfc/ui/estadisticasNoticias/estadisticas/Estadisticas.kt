package com.utad.pfc.ui.estadisticasNoticias.estadisticas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utad.pfc.R
import com.utad.pfc.databinding.FragmentEstadisticasBinding
import com.utad.pfc.ui.estadisticasNoticias.ViewPagerAdapter
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.estAmarillas.EstAmarillas
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.estAsistencias.EstAsistencias
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.estGoles.EstGoles
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.estRojas.EstRojas
import com.utad.pfc.ui.estadisticasNoticias.noticias.Noticias

class Estadisticas : Fragment() {
    private var _binding: FragmentEstadisticasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEstadisticasBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }
    private fun setupUI(){
        setupViewpager()
        binding.tabLayout2.setupWithViewPager(binding.viewPager2)
    }

    private fun setupViewpager() {
        val adapter = fragmentManager?.let { ViewPagerAdapter(it) }
        adapter?.apply {
            addFragment(EstGoles(), "Goles")
            addFragment(EstAsistencias(), "Asistencias")
            addFragment(EstAmarillas(), "Amarillas")
            addFragment(EstRojas(), "Rojas")
        }

        binding.viewPager2.adapter = adapter
    }
}