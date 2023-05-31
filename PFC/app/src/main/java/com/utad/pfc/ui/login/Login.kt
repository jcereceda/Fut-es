package com.utad.pfc.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.utad.pfc.API.ApiRest
import com.utad.pfc.R
import com.utad.pfc.databinding.FragmentLoginBinding
import com.utad.pfc.model.DatosLogin
import com.utad.pfc.model.Usuario
import com.utad.pfc.ui.Menu
import com.utad.pfc.ui.pantallasInit.Iniciacion
import com.utad.pfc.ui.registro.Registro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class Login : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private lateinit  var lblError: TextView
    private lateinit  var cargandoLogin: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("UsrLogin", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()

        lblError = binding.lblError
        cargandoLogin = binding.pbLogin

        cargandoLogin.isVisible = false

        // Asignar fondo aleatorio
        val pantalla = view.findViewById<ConstraintLayout>(R.id.pantallaLogin)
        val fondos = arrayListOf(R.drawable.fondo1,R.drawable.fondo2, R.drawable.fondo3)
        pantalla.setBackgroundResource(fondos.random())

        val butonLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnIrRegistro =  view.findViewById<Button>(R.id.btnIrRegistro)
        var tfEmail = binding.tfEmail
        var tfPass = binding.tfPass



        butonLogin.setOnClickListener {
            cargandoLogin.isVisible = true
            editor.putString("email",tfEmail.text.toString())
            editor.putString("pass",getHash(tfPass.text.toString()))
            editor.apply()

            val call = ApiRest.service.login(DatosLogin(sharedPreferences.getString("email",""),sharedPreferences.getString("pass","")))
            call.enqueue(object : Callback<Usuario> {
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                    val user = response.body()
                    if (response.isSuccessful && user != null) {
                        val isFirstStart = sharedPreferences.getBoolean("isFirstStart", true)
                        if(isFirstStart){
                            ApiRest.UserLogged = user
                            val intent = Intent(requireContext(), Iniciacion::class.java)
                            requireContext().startActivity(intent)
                            val editor = sharedPreferences.edit()
                            editor.putBoolean("isFirstStart", false)
                            editor.apply()
                            activity?.finish()
                        } else {
                            val intent = Intent(requireContext(), Menu::class.java)
                            requireContext().startActivity(intent)
                            Log.i("Login","LLega")
                            activity?.finish()
                            ApiRest.UserLogged = user
                            cargandoLogin.isVisible = false
                        }
                    } else {
                        cargandoLogin.isVisible = false
                        lblError.text = "Usuario o contraseña invalidos"
                        Log.e("Login", response.errorBody()?.string() ?: "error")
                    }
                }

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    cargandoLogin.isVisible = false
                    lblError.text = "Usuario o contraseña invalidos"
                }

            })

        }


        btnIrRegistro?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.conteiner, Registro())?.commit()
        }
    }

    fun getHash(input: String): String {
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

}