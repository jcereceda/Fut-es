package com.utad.pfc.ui

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.utad.pfc.API.ApiRest
import com.utad.pfc.R
import com.utad.pfc.model.DatosLogin
import com.utad.pfc.model.Usuario
import com.utad.pfc.ui.login.Login
import com.utad.pfc.ui.pantallasInit.Iniciacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Borrar las shared preferences
        //val editor = getSharedPreferences("UsrLogin", MODE_PRIVATE).edit()
        //editor.clear().apply()
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        ApiRest.initService()
        val sharedPreferences = getSharedPreferences("UsrLogin", Context.MODE_PRIVATE)
        val call = ApiRest.service.login(
            DatosLogin(
                sharedPreferences.getString("email", ""),
                sharedPreferences.getString("pass", "")
            )
        )
        call.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                val user = response.body()
                if (response.isSuccessful && user != null) {
                    ApiRest.UserLogged = user
                    val isFirstStart = sharedPreferences.getBoolean("isFirstStart", true)
                    if (isFirstStart) {
                        startActivity(Intent(this@MainActivity, Iniciacion::class.java))
                        val editor = sharedPreferences.edit()
                        editor.putBoolean("isFirstStart", false)
                        editor.apply()

                    } else {
                        val intent = Intent(this@MainActivity, Menu::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    supportFragmentManager.beginTransaction().replace(R.id.conteiner, Login())
                        .commit()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                supportFragmentManager.beginTransaction().replace(R.id.conteiner, Login()).commit()
                Log.i("MainActivity", "vuelve a inciar sesion")
            }

        })

    }
}