package com.utad.pfc.model


import java.util.Date
import kotlin.properties.Delegates

class Usuario(

) {
    lateinit var accessToken: String
    lateinit var apellidos: String
    lateinit var email: String
    lateinit var fechaNac: Date
    var id: Int = 0
    lateinit var passwd: String
    lateinit var nombre: String
    lateinit var rol: String
    var id_equipo_fav: Int = 0
    var id_jugador_fav: Int = 0
    var foto: String = ""
}