package com.utad.pfc.model

/**
 * Clase para guardar los eventos que pueda tener un jugador
 * Goles
 * Asitencias
 * Tarjetas Rojas o Amarillas
 */
class EventoJugador {
    lateinit var tipoEvento: String
    var id_jugador = 0
    var id_partido = 0
    var minuto = 0
    lateinit var color: String // Aplicable solo a tarjetas
}