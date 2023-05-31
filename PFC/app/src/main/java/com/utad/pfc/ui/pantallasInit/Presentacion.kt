package com.utad.pfc.ui.pantallasInit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.utad.pfc.R
import com.utad.pfc.model.Equipo
import com.utad.pfc.ui.pantallasInit.EscogerEquipo.EscogerEquipo
import com.utad.pfc.ui.partidos.PartidosViewModel
import kotlinx.coroutines.launch


class Presentacion : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_presentacion, container, false)
    }
    private val viewModelEquipos: PartidosViewModel by activityViewModels()
    private lateinit var equipos: java.util.ArrayList<Equipo>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn = view.findViewById<Button>(R.id.comencemos)
        listenEvents()
        viewModelEquipos.getEquipos()
        btn.setOnClickListener {
            val fragment = EscogerEquipo()
            val bundle = Bundle()
            bundle.putSerializable("equipos",equipos)
            fragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.conteinerIniciacion, fragment)?.commit()
        }
    }
    fun listenEvents(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModelEquipos.equipos.collect{
                        equipos = it
                    }
                }
            }


        }
    }


}