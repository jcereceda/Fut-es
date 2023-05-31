package com.utad.pfc.ui.partidos.datos_partido

import android.provider.Telephony.Mms.Part
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.pfc.API.ApiRest
import com.utad.pfc.model.Arbitro
import com.utad.pfc.model.EventoJugador
import com.utad.pfc.model.Jugador
import com.utad.pfc.model.Partido
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DatosPartidosViewModel : ViewModel() {

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
                Log.i("DatosPartidoVM", "Arbitro:${response.errorBody()?.string()}")
            }
        })

    }

    val golesPartido = MutableStateFlow(arrayListOf<EventoJugador>())
    fun getGolesPartido(id_partido: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getGolesPorPartido(id_partido)
            if (response.isSuccessful && response.body() != null) {
                golesPartido.value = (response.body() as ArrayList<EventoJugador>)!!
            } else {
                Log.i("DatosPartidoVM", "Goles:${response.errorBody()?.string()}")
            }
        })
    }

    val jugadoresLocal = MutableStateFlow(arrayListOf<Jugador>())
    fun getJugadoresLocal(id_equipo: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()

            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getJugadoresPorEquipo(id_equipo)
            if (response.isSuccessful && response.body() != null) {
                jugadoresLocal.value = (response.body() as ArrayList<Jugador>)!!

            } else {
                Log.i("DatosPartidoVM", "Goles:${response.errorBody()?.string()}")

            }

        })
    }


    val jugadoresVisitante = MutableStateFlow(arrayListOf<Jugador>())
    fun getJugadoresVisitante(id_equipo: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getJugadoresPorEquipo(id_equipo)
            if (response.isSuccessful && response.body() != null) {
                jugadoresVisitante.value = (response.body() as ArrayList<Jugador>)!!
            } else {
                Log.i("DatosPartidoVM", "Goles:${response.errorBody()?.string()}")
            }
        })
    }

    val amarillasPartido = MutableStateFlow(arrayListOf<EventoJugador>())
    fun getAmarillasPartido(id_parido: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getTarjetasPorPartido("amarillas",id_parido)
            if (response.isSuccessful && response.body() != null) {
                amarillasPartido.value = (response.body() as ArrayList<EventoJugador>)!!
            } else {
                Log.i("DatosPartidoVM", "Amarillas:${response.errorBody()?.string()}")
            }
        })
    }

    val rojasPartido = MutableStateFlow(arrayListOf<EventoJugador>())
    fun getRojasPartido(id_parido: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getTarjetasPorPartido("rojas",id_parido)
            if (response.isSuccessful && response.body() != null) {
                rojasPartido.value = (response.body() as ArrayList<EventoJugador>)!!
            } else {
                Log.i("DatosPartidoVM", "Amarillas:${response.errorBody()?.string()}")
            }
        })
    }
    val partido = MutableStateFlow(Partido())
    fun getPartido(id: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getOnePartido(id);
            if (response.isSuccessful && response.body() != null) {
                partido.value = (response.body() as Partido)!!
            } else {
                Log.i("DatosPartidoVM", "Partido:${response.errorBody()?.string()}")
            }
        })

    }


}