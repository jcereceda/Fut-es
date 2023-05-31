package com.utad.pfc.model

data class Jugador(
    val id: Int,
    val nombre: String,
    val apodo: String,
    val peso: Int,
    val altura: Double,
    val foto: String,
    val fondo: String,
    val dorsal: Int,
    val id_equipo: Int,
    val posicion: String
) : java.io.Serializable