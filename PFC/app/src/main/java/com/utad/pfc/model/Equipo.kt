package com.utad.pfc.model

data class Equipo(
    val id: Int,
    val nombre: String,
    val nombre_abrev: String,
    val descripcion: String,
    var escudo: String,
    val estadio: String,
    val presi: String
) : java.io.Serializable