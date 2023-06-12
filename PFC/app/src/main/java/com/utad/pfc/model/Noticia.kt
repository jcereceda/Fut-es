package com.utad.pfc.model

import java.sql.Date

class Noticia(

): java.io.Serializable {
    val id: Int = 0
    var titular: String = ""
    var cuerpo: String = ""
    var foto: String = ""
    var id_periodista: Int = 0
    var fecha: String = ""
}
