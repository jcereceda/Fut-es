package com.utad.pfc.ui.estadisticasNoticias.estadisticas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.pfc.API.ApiRest
import com.utad.pfc.model.Jugador
import com.utad.pfc.model.ResponseEstadisticas
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EstadisticasViewModel: ViewModel() {

    val coroutineExceptionHandler : CoroutineExceptionHandler =
        (CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        })


    val goles = MutableStateFlow(arrayListOf<ResponseEstadisticas>())
    val loading = MutableStateFlow(false)
    fun getEstadisticasGoles() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getEstadisticas("goles")
            if (response.isSuccessful && response.body() != null) {
                goles.value = (response.body() as ArrayList<ResponseEstadisticas>?)!!
            } else {
                Log.i("EstadisticasViewModel" , "Goles:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val asistencias = MutableStateFlow(arrayListOf<ResponseEstadisticas>())
    fun getEstadisticasAsistencias() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getEstadisticas("asistencias")
            if (response.isSuccessful && response.body() != null) {
                asistencias.value = (response.body() as ArrayList<ResponseEstadisticas>?)!!
            } else {
                Log.i("EstadisticasViewModel" , "asistencias:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val tarjetasAmarillas = MutableStateFlow(arrayListOf<ResponseEstadisticas>())
    fun getEstadisticasAmarillas() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getTarjetas("amarilla")
            if (response.isSuccessful && response.body() != null) {
                tarjetasAmarillas.value = (response.body() as ArrayList<ResponseEstadisticas>?)!!
            } else {
                Log.i("EstadisticasViewModel" , "amarillas:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val tarjetasRojas = MutableStateFlow(arrayListOf<ResponseEstadisticas>())
    fun getEstadisticasRojas() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getTarjetas("rojas")
            if (response.isSuccessful && response.body() != null) {
                tarjetasRojas.value = (response.body() as ArrayList<ResponseEstadisticas>?)!!
            } else {
                Log.i("EstadisticasViewModel" , "Goles:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }


    val jugadoresConGol = MutableStateFlow(arrayListOf<Jugador>())
    fun getJugadoresGol() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getJugadoresEstads("gol")
            if (response.isSuccessful && response.body() != null) {
                jugadoresConGol.value = (response.body() as ArrayList<Jugador>?)!!
            } else {
                Log.i("EstadisticasViewModel" , "jugadores:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val jugadoresConAsistencias = MutableStateFlow(arrayListOf<Jugador>())
    fun getJugadoresAsistencia() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getJugadoresEstads("asistencia")
            if (response.isSuccessful && response.body() != null) {
                jugadoresConAsistencias.value = (response.body() as ArrayList<Jugador>?)!!
            } else {
                Log.i("EstadisticasViewModel" , "jugadores:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val jugadoresConTarjetas = MutableStateFlow(arrayListOf<Jugador>())
    fun getJugadoresTarjetas() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getJugadoresEstads("tarjeta")
            if (response.isSuccessful && response.body() != null) {
                jugadoresConTarjetas.value = (response.body() as ArrayList<Jugador>?)!!
            } else {
                Log.i("EstadisticasViewModel" , "jugadores:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

}