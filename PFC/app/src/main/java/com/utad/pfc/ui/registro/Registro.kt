package com.utad.pfc.ui.registro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.utad.pfc.API.ApiRest
import com.utad.pfc.R
import com.utad.pfc.databinding.FragmentRegistroBinding
import com.utad.pfc.model.Usuario
import com.utad.pfc.ui.Menu
import com.utad.pfc.ui.login.Login
import com.utad.pfc.ui.pantallasInit.Iniciacion
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*


class Registro : Fragment() {

    private var _binding: FragmentRegistroBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private var firstTime = true;
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Asignar fondo aleatorio
        val pantalla = view.findViewById<ConstraintLayout>(R.id.pantallaRegistro)
        val fondos = arrayListOf(R.drawable.fondo1,R.drawable.fondo2, R.drawable.fondo3)
        pantalla.setBackgroundResource(fondos.random())

        val btnIrLogin = binding.btnALogin
        val btnRegistrar = binding.btnRegister
        val campoFechaNac = binding.FechaNac

        // Poner fecha nacimiento
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat)
        var date = Date()
        val timeInMillis = date.time
        val constraintBuilder = CalendarConstraints.Builder().setOpenAt(timeInMillis).build()
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Fecha de nacimiento")
            .setCalendarConstraints(constraintBuilder)
            .build()

        binding.FechaNac.setEndIconOnClickListener {
            // Botón de icono textfield de fecha de nacimineto
            datePicker.show(getParentFragmentManager(),"datepicker")
        }
        // Al pulsar el boton de ok del datepicker
        datePicker.addOnPositiveButtonClickListener {
            date = Date(it)
            var formattedDate = ""
            formattedDate = sdf.format(date)
            binding.tfFechaNac.setText(formattedDate)
        }

        btnIrLogin.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.conteiner, Login())?.commit()
        }

        // Comprobar campos de texto
        binding.tfEmail.doAfterTextChanged {
            var email: String = binding.tfEmail.text.toString()
            if (!isEmailValid(email) && !firstTime) {
                binding.layEmail.error = "Formato invalido"
            } else {
                binding.layEmail.error = null
            }
        }
        binding.tfNombre.doAfterTextChanged {
            if (binding.tfNombre.text.toString() == "" && !firstTime) {
                binding.layNombre.error = "Completar campo"
            } else {
                binding.layNombre.error = null
            }
        }
        binding.tfApellidos.doAfterTextChanged {
            if (binding.tfApellidos.text.toString() == "" && !firstTime) {
                binding.layApellidos.error = "Completar campo"
            } else {
                binding.layApellidos.error = null
            }
        }
        binding.tfFechaNac.doAfterTextChanged {
            if (binding.tfFechaNac.text.toString() == "" && !firstTime) {
                binding.FechaNac.error = "Completar campo"
            } else {
                binding.FechaNac.error = null
            }
        }
        binding.tfPassword.doAfterTextChanged {
            if (binding.tfPassword.text.toString() == "" && !firstTime) {
                binding.layPassword.error = "Completar campo"
            } else {
                binding.layPassword.error = null
            }
        }
        binding.tfConfirmPass.doAfterTextChanged {
            if (getHash(binding.tfPassword.text.toString()) != getHash(binding.tfConfirmPass.text.toString()) && !firstTime) {
                binding.layConfirmPass.error = "No coincidentes"
            } else {
                binding.layConfirmPass.error = null
            }
        }
        btnRegistrar.setOnClickListener{
            firstTime = false;
            val user = Usuario()
            if(comprobarValores()){
                user.email = binding.tfEmail.text.toString()
                user.nombre = binding.tfNombre.text.toString()
                user.apellidos = binding.tfApellidos.text.toString()
                user.fechaNac = date
                user.passwd = getHash(binding.tfPassword.text.toString())
                registrar(user)
            }

        }
    }

    private fun registrar(user: Usuario) {
        val call = ApiRest.service.crearUser(user)
        call.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                val user = response.body()
                if (response.isSuccessful && user != null) {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.conteiner, Login())?.commit()
                    Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                } else {
                    binding.layEmail.error = "Email en uso"
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                binding.layEmail.error = "Email en uso"
            }

        })
    }


    private fun comprobarValores(): Boolean {
        var isValid = true
        var email: String = binding.tfEmail.text.toString()
        if (!isEmailValid(email)){
            isValid = false
            binding.layEmail.error = "Formato invalido"
        }

        if(binding.tfNombre.text.toString() == "") {
            isValid = false
            binding.layNombre.error = "Completar campo"
        }

        if(binding.tfApellidos.text.toString() == "") {
            isValid = false
            binding.layApellidos.error = "Completar campo"
        }

        if(binding.tfFechaNac.text.toString() == "") {
            isValid = false
            binding.FechaNac.error = "Completar campo"
        }


        if(binding.tfPassword.text.toString() == "") {
            isValid = false
            binding.layPassword.error = "Completar campo"
        }

        if(getHash(binding.tfPassword.text.toString()) != getHash(binding.tfConfirmPass.text.toString())){
            isValid = false
            binding.layConfirmPass.error = "No coincidentes"
        }

        return isValid;
    }

    // Sacar codigo hash de un string
    private fun getHash(input: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val hash = md.digest(input.toByteArray(StandardCharsets.UTF_8))
        val hexString = StringBuilder()
        for (byte in hash) {
            val hex = Integer.toHexString(0xff and byte.toInt())
            if (hex.length == 1) {
                hexString.append('0')
            }
            hexString.append(hex)
        }
        return hexString.toString()
    }

    // Método para saber si el email tiene formato correcto
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    private fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }

}