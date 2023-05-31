package com.utad.pfc.ui.perfil.modificar_perfil

import android.R
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.utad.pfc.API.ApiRest
import com.utad.pfc.databinding.FragmentModificarPerfiBinding
import com.utad.pfc.model.Equipo
import com.utad.pfc.model.Jugador
import com.utad.pfc.model.Usuario
import com.utad.pfc.ui.perfil.PerfilViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class ModificarPerfi : Fragment() {

    private lateinit var toolbar: Toolbar
    private var _binding: FragmentModificarPerfiBinding? = null
    lateinit var equipos: java.util.ArrayList<Equipo>
    lateinit var plantilla: java.util.ArrayList<Jugador>
    lateinit var jugadorFav: Jugador
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        equipos = arguments?.getSerializable("equipos") as ArrayList<Equipo>
        if(ApiRest.UserLogged.id_jugador_fav != 0) {
            jugadorFav = arguments?.getSerializable("jugador") as Jugador
        }
        plantilla = arguments?.getSerializable("plantilla") as ArrayList<Jugador>

        _binding = FragmentModificarPerfiBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    private val PerflviewModel: PerfilViewModel by activityViewModels()
    private lateinit var equipo: Equipo
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tfEmail.setText(ApiRest.UserLogged.email)
        binding.tfApellidos.setText(ApiRest.UserLogged.apellidos)
        binding.tfNombre.setText(ApiRest.UserLogged.nombre)

        listenEvents()

        toolbar = view.findViewById(com.utad.pfc.R.id.toolbarEditPerfil)
        toolbar.setNavigationOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
        }

        toolbar.title = "Modificar perfil"

        val items = equipos.map { equipo -> equipo.nombre_abrev}
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, items)
        binding.tfModifEquipo.setAdapter(adapter)
        val adapterJugadores = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, plantilla.map { jugador -> jugador.apodo })
        binding.tfModifJugador.setAdapter(adapterJugadores)

        if(ApiRest.UserLogged.id_equipo_fav != 0){
            equipo = equipos.filter{ it.id == ApiRest.UserLogged.id_equipo_fav }.single()
            binding.tfModifEquipo.setText(equipos.filter{ it.id == ApiRest.UserLogged.id_equipo_fav }.single().nombre_abrev, false)
            binding.tfModifJugador.setText(jugadorFav.dorsal.toString() + ". " + jugadorFav.apodo, false)
        }

        binding.modifEquipoFav.setEndIconOnClickListener {
            binding.tfModifEquipo.showDropDown()
        }
        binding.modifJugadorFav.setEndIconOnClickListener {
            binding.tfModifJugador.showDropDown()
        }

        binding.tfModifEquipo.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            equipo =  equipos.filter { it.nombre_abrev == adapterView.getItemAtPosition(position) }.single()
            PerflviewModel.getJugadoresEquipo(equipo.id)
            adapterJugadores.notifyDataSetChanged()
        }

        binding.tfModifJugador.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            jugadorFav =  plantilla.filter { it.apodo == adapterView.getItemAtPosition(position) }.single()
        }

        binding.btnGuardarCambios.setOnClickListener {
            guardarCambios()
        }

    }

    private fun guardarCambios() {
        // Coger datos y enviar a la API
        val user = Usuario()
        user.id = ApiRest.UserLogged.id
        user.nombre = binding.tfNombre.text.toString()
        user.apellidos = binding.tfApellidos.text.toString()
        user.email = binding.tfEmail.text.toString()
        //user.fechaNac = binding.tfModifFechaNac.text.todate
        user.id_equipo_fav = equipo.id
        user.id_jugador_fav = jugadorFav.id
        PerflviewModel.actualizarPerfil(user)
    }


    private fun listenEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    PerflviewModel.plantilla.collect {
                        plantilla = it
                    }
                }
                launch {
                    PerflviewModel.usuario.collect{
                        if(it.id != 0){
                            activity?.supportFragmentManager?.popBackStack()
                        }
                        it.id = 0
                    }
                }
            }
        }
    }


}