package com.utad.pfc.ui.partidos.completar_partido

import android.media.metrics.Event
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.pfc.API.ApiRest
import com.utad.pfc.model.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CompletarPartidoViewModel : ViewModel() {


    val arbitro = MutableStateFlow(Arbitro(0, ""))
    fun getArbitro(id: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getArbitro(id)
            if (response.isSuccessful && response.body() != null) {
                arbitro.value = (response.body() as Arbitro)!!
            } else {
                Log.i("CompletarPartidoVM", "Arbitro:${response.errorBody()?.string()}")
            }
        })
    }

    fun putEvento(id_partido: Int, evento: String) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            ApiRest.service.putEvento(ApiRest.UserLogged.accessToken,id_partido, evento)
        })
    }

    fun putPosesion(id_partido: Int, posesion: Posesion) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            ApiRest.service.putPosesion(ApiRest.UserLogged.accessToken, id_partido, posesion)
        })
    }

    val plantillaLocal = MutableStateFlow(arrayListOf<Jugador>())

    fun getJugadoresLocal(id_equipo: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getJugadoresPorEquipo(id_equipo)
            if (response.isSuccessful && response.body() != null) {
                plantillaLocal.value = (response.body() as ArrayList<Jugador>)!!
            } else {
                Log.i("CompletarPartidoVM", "Jugadores local:${response.errorBody()?.string()}")
            }
        })
    }

    val plantillaVisitante = MutableStateFlow(arrayListOf<Jugador>())

    fun getJugadoresVisitante(id_equipo: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getJugadoresPorEquipo(id_equipo)
            if (response.isSuccessful && response.body() != null) {
                plantillaVisitante.value = (response.body() as ArrayList<Jugador>)!!
            } else {
                Log.i("CompletarPartidoVM", "Jugadores visitante:${response.errorBody()?.string()}")
            }
        })
    }

    fun postEventoJugador(evento: EventoJugador) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            ApiRest.service.postEventoJugador(ApiRest.UserLogged.accessToken, evento.tipoEvento ,evento)
        })


    }

}