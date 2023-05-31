package com.utad.pfc.ui.partidos

import android.provider.Telephony.Mms.Part
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.utad.pfc.API.ApiRest
import com.utad.pfc.model.DatosLogin
import com.utad.pfc.model.Equipo
import com.utad.pfc.model.Partido
import com.utad.pfc.model.Usuario
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PartidosViewModel : ViewModel() {
    val partidos = MutableStateFlow(arrayListOf<Partido>())
    val loading = MutableStateFlow(false)
    val loadingEquipos = MutableStateFlow(false)

    val coroutineExceptionHandler: CoroutineExceptionHandler =
        (CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        })


    fun getPartidos() {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        loading.value = true
        loadingEquipos.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getPartidos()
            if (response.isSuccessful && response.body() != null) {
                partidos.value = (response.body() as ArrayList<Partido>?)!!
            } else {
                Log.i("PartidosViewModel", "Partidos:${response.errorBody()?.string()}")
            }
            loading.value = false
            loadingEquipos.value = false
        })
    }

    val equipos = MutableStateFlow(arrayListOf<Equipo>())
    fun getEquipos() {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getEquipos()
            if (response.isSuccessful && response.body() != null) {
                equipos.value = (response.body() as ArrayList<Equipo>?)!!
            } else {
                Log.i("PartidosViewModel", "Equipos:${response.errorBody()?.string()}")
            }
            loading.value = false
        })
    }

    fun getPartidosEquipo(id_equipo: Int) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.getPartidosEquipo(id_equipo)
            if (response.isSuccessful && response.body() != null) {
                partidos.value = (response.body() as ArrayList<Partido>?)!!
            } else {
                Log.i("PartidosViewModel", "Equipos:${response.errorBody()?.string()}")
            }
            loading.value = false
        })
    }

    /*
    val hayPartido = MutableStateFlow(false)
    val partidoPost = MutableStateFlow(Partido())
    val msgError = MutableStateFlow("")

    fun guardarPartido(partido: Partido) {

        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
                hayPartido.value = false
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response = ApiRest.service.postPartido(ApiRest.UserLogged.accessToken, partido)

            if (response.isSuccessful && response.body() != null) {
                partidoPost.value = (response.body() as Partido)!!
                hayPartido.value = true
            } else {
                hayPartido.value = false
                msgError.value = response.errorBody()?.string().toString()
                Log.i("PartidosViewModel", "postPartido:" + msgError.value)
            }
            hayPartido.value = false

        })
    }
*/
}