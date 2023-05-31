package com.utad.pfc.model

import java.sql.Date

data class Noticia(
    val id: Int,
    val titular: String,
    val cuerpo: String,
    val foto: String,
    val id_periodista: Int,
    val fecha: String
): java.io.Serializable
