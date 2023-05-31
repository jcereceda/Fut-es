package com.utad.pfc.ui.pantallasInit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.pfc.API.ApiRest
import com.utad.pfc.model.Equipo
import com.utad.pfc.model.Jugador
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class IniciacionViewModel: ViewModel() {

    fun putEquipoFav(equipo: Equipo) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            ApiRest.service.actualizarEquipoFav(ApiRest.UserLogged.accessToken, ApiRest.UserLogged.id, equipo.id)
        })
    }
    val jugadores = MutableStateFlow(arrayListOf<Jugador>())

    fun getJugadoresEquipo(id: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getJugadoresPorEquipo(id)
            if (response.isSuccessful && response.body() != null) {
                jugadores.value = (response.body() as ArrayList<Jugador>)!!
            } else {
                Log.i("DatosPartidoVM", "Jugadores:${response.errorBody()?.string()}")

            }
        })
    }

    fun putJugadorFav(jugador: Jugador) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            ApiRest.service.actualizarJugadorFav(ApiRest.UserLogged.accessToken, ApiRest.UserLogged.id, jugador.id)
        })
    }



}