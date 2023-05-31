package com.utad.pfc.ui.estadisticasNoticias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utad.pfc.databinding.FragmentEstadisticasNoticiasBinding
import com.utad.pfc.ui.estadisticasNoticias.estadisticas.Estadisticas
import com.utad.pfc.ui.estadisticasNoticias.noticias.Noticias


class EstadisticasNoticias : Fragment() {
    private var _binding: FragmentEstadisticasNoticiasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentEstadisticasNoticiasBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            binding.viewPager.currentItem = savedInstanceState.getInt("currentViewPagerItem", 0)
        }
        setupUI()
    }

    private fun setupUI(){
        setupViewpager()
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupUI()
        if (savedInstanceState != null) {
            binding.viewPager.currentItem = savedInstanceState.getInt("currentViewPagerItem", 0)
        }
    }
    private fun setupViewpager() {
        val adapter = fragmentManager?.let { ViewPagerAdapter(it) }
        adapter?.apply {
            addFragment(Estadisticas(), "Estadisticas")
            addFragment(Noticias(), "Noticias")
        }
        binding.viewPager.adapter = adapter
    }
}