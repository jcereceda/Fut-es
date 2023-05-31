package com.utad.pfc.ui.perfil

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.Api
import com.utad.pfc.API.ApiRest
import com.utad.pfc.model.Jugador
import com.utad.pfc.model.Partido
import com.utad.pfc.model.ResponseEstadisticas
import com.utad.pfc.model.Usuario
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PerfilViewModel : ViewModel() {

    val coroutineExceptionHandler : CoroutineExceptionHandler =
        (CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        })

    val jugador = MutableStateFlow(Jugador(0,"","",0,0.0,"","",-1,0,""))

    fun getJugador(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getJugador(id)
            if (response.isSuccessful && response.body() != null) {
                jugador.value = (response.body() as Jugador)!!
            } else {
                Log.i("PerfilViewModel" , "Jugador:${response.errorBody()?.string() }")
            }
        })
    }

    val cantGoles = MutableStateFlow(ResponseEstadisticas(0,0))
    fun getCantGoles(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getEstadisticasJugador("goles",id)
            if (response.isSuccessful && response.body() != null) {
                cantGoles.value = (response.body() as ResponseEstadisticas)!!
            } else {
                Log.i("PerfilViewModel" , "goles:${response.errorBody()?.string() }")
            }
        })
    }

    val cantAsistencias = MutableStateFlow(ResponseEstadisticas(0,0))
    fun getCantAsistencias(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getEstadisticasJugador("asistencias",id)
            if (response.isSuccessful && response.body() != null) {
                cantAsistencias.value = (response.body() as ResponseEstadisticas)!!
            } else {
                Log.i("PerfilViewModel" , "asistencias:${response.errorBody()?.string() }")
            }
        })
    }

    val cantAmarillas = MutableStateFlow(ResponseEstadisticas(0,0))
    fun getCantAmarillas(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getEstadisticasJugador("amarillas",id)
            if (response.isSuccessful && response.body() != null) {
                cantAmarillas.value = (response.body() as ResponseEstadisticas)!!
            } else {
                Log.i("PerfilViewModel" , "amarillas:${response.errorBody()?.string() }")
            }
        })
    }

    val cantRojas = MutableStateFlow(ResponseEstadisticas(0,0))
    fun getCantRojas(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getEstadisticasJugador("rojas",id)
            if (response.isSuccessful && response.body() != null) {
                cantRojas.value = (response.body() as ResponseEstadisticas)!!
            } else {
                Log.i("PerfilViewModel" , "rojas:${response.errorBody()?.string() }")
            }
        })
    }

    val plantilla = MutableStateFlow(arrayListOf<Jugador>())

    fun getJugadoresEquipo(id_equipo: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getJugadoresPorEquipo(id_equipo)
            if (response.isSuccessful && response.body() != null) {
                plantilla.value = (response.body() as ArrayList<Jugador>)!!
            } else {
                Log.i("PerfilViewModel", "Jugadores:${response.errorBody()?.string()}")
            }
        })
    }


    val usuario = MutableStateFlow(Usuario())

    fun actualizarPerfil(user: Usuario){
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.actualizar(ApiRest.UserLogged.accessToken,user)
            if (response.isSuccessful && response.body() != null) {
                usuario.value = response.body() as Usuario
                Log.i("perfilVM", usuario.value.apellidos)
                ApiRest.UserLogged.nombre = usuario.value.nombre
                ApiRest.UserLogged.apellidos = usuario.value.apellidos
                ApiRest.UserLogged.email = usuario.value.email
                ApiRest.UserLogged.fechaNac = usuario.value.fechaNac
                ApiRest.UserLogged.id_equipo_fav = usuario.value.id_equipo_fav
                ApiRest.UserLogged.id_jugador_fav = usuario.value.id_jugador_fav
            } else {
                Log.i("PerfilViewModel", "Usuario Actualizado: ${response.errorBody()?.string()}")
            }
        })
    }

}