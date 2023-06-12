package com.utad.pfc.API

import com.utad.pfc.model.Usuario
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRest {
    lateinit var service: ApiService
    //val URL = "http://192.168.115.246:8080/api/"
    val URL = "http://jcereceda.duckdns.org/api/"
    var UserLogged:Usuario = Usuario()

    init {
        initService()
    }

    fun initService () {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory( GsonConverterFactory .create())
            .build()
        service = retrofit.create(ApiService ::class.java)
    }
}