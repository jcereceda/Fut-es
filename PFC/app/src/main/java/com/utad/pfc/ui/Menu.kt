package com.utad.pfc.ui

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.utad.pfc.R
import com.utad.pfc.databinding.ActivityMenuBinding
import com.utad.pfc.ui.clasificacion.Tabla_clasificacion
import com.utad.pfc.ui.estadisticasNoticias.EstadisticasNoticias
import com.utad.pfc.ui.pantallasInit.Iniciacion
import com.utad.pfc.ui.partidos.Listado_partidos
import com.utad.pfc.ui.perfil.Perfil

class Menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var bottom_navigation_view: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottom_navigation_view = binding.bottomNavigationView
        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.partidos -> {
                    goToFragment(Listado_partidos(), "listado")
                    true
                }
                R.id.clasificacion -> {
                    goToFragment(Tabla_clasificacion(), "tabla")
                    true
                }
                R.id.estadisticas -> {
                    goToFragment(EstadisticasNoticias(),"estadisticasNoticias")
                    true
                }
                R.id.perfil -> {
                    goToFragment(Perfil(),"perfil")
                    true
                }
                else -> false
            }
        }

        bottom_navigation_view.selectedItemId = R.id.partidos

    }

    fun goToFragment(fragment: Fragment, tag: String) {
     supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment, tag).commit()
    }


}