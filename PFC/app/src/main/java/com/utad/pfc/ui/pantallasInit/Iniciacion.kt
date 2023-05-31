package com.utad.pfc.ui.pantallasInit

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.utad.pfc.R
import com.utad.pfc.ui.login.Login

class Iniciacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciacion)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportFragmentManager.beginTransaction().replace(R.id.conteinerIniciacion, Presentacion()).commit()
    }

}