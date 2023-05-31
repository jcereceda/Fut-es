package com.utad.pfc.API

import com.utad.pfc.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("login")
    fun login(
        @Body data: DatosLogin
    ): Call<Usuario>

    @POST("usuarios")
    fun crearUser(@Body data: Usuario): Call<Usuario>

    @PUT("usuarios/actualizar")
    suspend fun actualizar(
        @Header("x-access-token") token: String,
        @Body data: Usuario
    ): Response<Usuario>

    @PUT("usuarios/{id}/equipo/{id_equipo_fav}")
    suspend fun actualizarEquipoFav(
        @Header("x-access-token") token: String,
        @Path("id") id: Int,
        @Path("id_equipo_fav") id_equipo_fav: Int
    )

    @PUT("usuarios/{id}/jugador/{id_jugador_fav}")
    suspend fun actualizarJugadorFav(
        @Header("x-access-token") token: String,
        @Path("id") id: Int,
        @Path("id_jugador_fav") id_jugador_fav: Int
    )

    @GET("equipos")
    suspend fun getEquipos(): Response<List<Equipo>>

    // Partidos
    @GET("partidos")
    suspend fun getPartidos(): Response<List<Partido>>

    @GET("partidos/equipo/{id_equipo}")
    suspend fun getPartidosEquipo(
        @Path("id_equipo") id: Int
    ): Response<List<Partido>>

    @GET("arbitros/{id}")
    suspend fun getArbitro(
        @Path("id") id: Int
    ): Response<Arbitro>

    @PUT("partidos/{id}/{evento}")
    suspend fun putEvento(
        @Header("x-access-token") token: String,
        @Path("id") id: Int,
        @Path("evento") evento: String
    )

    @PUT("partidos/{id}/posesion")
    suspend fun putPosesion(
        @Header("x-access-token") token: String,
        @Path("id") id: Int,
        @Body posesion: Posesion
    )

    @POST("incidencias/{tipo}")
    suspend fun postEventoJugador(
        @Header("x-access-token") token: String,
        @Path("tipo") tipo: String,
        @Body evento: EventoJugador
    )

    @GET("incidencias/goles/partido/{id_partido}")
    suspend fun getGolesPorPartido(
        @Path("id_partido") id_partido: Int
    ): Response<List<EventoJugador>>


    @GET("jugadores/{id_equipo}")
    suspend fun getJugadoresPorEquipo(
        @Path("id_equipo") id_equipo: Int
    ): Response<List<Jugador>>
    // Clasificacion
    @GET("liga/{id}")
    suspend fun getLiga(
        @Path("id") id: Int
    ): Response<Liga>

    @GET("clasificacion/{id_liga}")
    suspend fun getClasificacion(
        @Path("id_liga") id_liga: Int
    ): Response<List<Clasificacion>>

    @GET("clasificacion/golescontra/{estado}")
    suspend fun getGolesContraEquipo(
        @Path("estado") estado: String
    ): Response<List<ResponseClasificacion>>

    @GET("clasificacion/golesfavor/{estado}")
    suspend fun getGolesFavorsEquipo(
        @Path("estado") estado: String
    ): Response<List<ResponseClasificacion>>

    @GET("clasificacion/partidosganados/{estado}")
    suspend fun getPartidosGanados(
        @Path("estado") estado: String
    ): Response<List<ResponseClasificacion>>

    @GET("clasificacion/partidosempatados/{estado}")
    suspend fun getPartidosEmpatados(
        @Path("estado") estado: String
    ): Response<List<ResponseClasificacion>>


    @GET("clasificacion/partidosperdidos/{estado}")
    suspend fun getPartidosPerdidos(
        @Path("estado") estado: String
    ): Response<List<ResponseClasificacion>>

    @GET("partidos/{id}")
    suspend fun getOnePartido(
        @Path("id") id: Int
    ):Response<Partido>

    // Estadisticas
    @GET("estadisticas/{tipo}")
    suspend fun getEstadisticas(
        @Path("tipo") tipo: String
    ): Response<List<ResponseEstadisticas>>

    @GET("estadisticas/tarjetas/{color}")
    suspend fun getTarjetas(
        @Path("color") color: String
    ): Response<List<ResponseEstadisticas>>

    @GET("jugadores/{tipo}")
    suspend fun getJugadoresEstads(
        @Path("tipo") tipo: String
    ): Response<List<Jugador>>

    // Noticias
    @GET("noticias")
    suspend fun getNoticias(): Response<List<Noticia>>

    @GET("jugador/{id}")
    suspend fun getJugador(
        @Path("id") id: Int
    ): Response<Jugador>

    @GET("comentarios/{id_noticia}")
    suspend fun getComentariosPorNoticia(
        @Header("x-access-token") token: String,
        @Path("id_noticia") id_noticia: Int
    ): Response<List<Comentario>>

    @POST("comentarios")
    suspend fun postComentario(
        @Header("x-access-token") token: String,
        @Body coment: Comentario
    )

    @GET("incidencias/{tipo}/jugador/{id}")
    suspend fun getEstadisticasJugador(
        @Path("tipo") tipo: String,
        @Path("id") id: Int
    ): Response<ResponseEstadisticas>

    @GET("incidencias/tarjetas/{color}/partido/{id_partido}")
    suspend fun getTarjetasPorPartido(
        @Path("color") color: String,
        @Path("id_partido") id_partido: Int
    ):Response<List<EventoJugador>>

    @POST("partidos")
    fun postPartido(
        @Header("x-access-token") token: String,
        @Body partido: Partido
    ): Call<Partido>
}