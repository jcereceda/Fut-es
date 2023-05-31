package com.utad.pfc.model

import kotlin.properties.Delegates


class Partido (

): java.io.Serializable {
    var id: Int = 0
    var id_equipo_local: Int = 0
    var id_equipo_visitante : Int = 0
    var id_liga : Int = 0
    var id_creador : Int = 0
    var id_arbitro : Int = 0
    var terminado: Boolean = false
    var goles_local : Int = 0
    var goles_visitante : Int = 0
    var posesion_local : Int = 0
    var posesion_visitante : Int = 0
    var pases_local : Int = 0
    var pases_visitante : Int = 0
    var tiros_local : Int = 0
    var tiros_visitante : Int = 0
    var corners_local : Int = 0
    var corners_visitante : Int = 0
    var faltas_local : Int = 0
    var faltas_visitante : Int = 0
    lateinit var fecha: String;
}