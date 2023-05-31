package com.utad.pfc.ui.clasificacion

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.pfc.API.ApiRest
import com.utad.pfc.model.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ClasificacionViewModel : ViewModel() {

    val coroutineExceptionHandler : CoroutineExceptionHandler =
        (CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        })

    val liga = MutableStateFlow(Liga(0,"",""))
    fun getLiga(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getLiga(id)
            if (response.isSuccessful && response.body() != null) {
                liga.value = (response.body() as Liga)!!
            } else {
                Log.i("ClasificacionViewModel" , "Liga:${response.errorBody()?.string() }")
            }
        })
    }

    val loading = MutableStateFlow(false)
    val posiciones = MutableStateFlow(ArrayList<Clasificacion>())
    fun getClasificacion(id_liga: Int) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getClasificacion(id_liga)
            if (response.isSuccessful && response.body() != null) {
                posiciones.value = (response.body() as ArrayList<Clasificacion>)!!
            } else {
                Log.i("ClasificacionViewModel" , "Posiciones:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val equipos = MutableStateFlow(arrayListOf<Equipo>())
    fun getEquipos() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getEquipos()
            if (response.isSuccessful && response.body() != null) {
                equipos.value = (response.body() as ArrayList<Equipo>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "Equipos:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val golesContraComoLocal = MutableStateFlow(arrayListOf<ResponseClasificacion>())
    fun getGolesContraComoLocal() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getGolesContraEquipo("local")
            if (response.isSuccessful && response.body() != null) {
                golesContraComoLocal.value = (response.body() as ArrayList<ResponseClasificacion>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "goles:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val golesContraComoVisitante = MutableStateFlow(arrayListOf<ResponseClasificacion>())
    fun getGolesContraComoVisitante() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getGolesContraEquipo("visitante")
            if (response.isSuccessful && response.body() != null) {
                golesContraComoVisitante.value = (response.body() as ArrayList<ResponseClasificacion>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "goles:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val golesFavorComoLocal = MutableStateFlow(arrayListOf<ResponseClasificacion>())
    fun getGolesFavorComoLocal() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getGolesFavorsEquipo("local")
            if (response.isSuccessful && response.body() != null) {
                golesFavorComoLocal.value = (response.body() as ArrayList<ResponseClasificacion>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "goles:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val golesFavorComoVisitante = MutableStateFlow(arrayListOf<ResponseClasificacion>())
    fun getGolesFavorComoVisitante() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getGolesFavorsEquipo("visitante")
            if (response.isSuccessful && response.body() != null) {
                golesFavorComoVisitante.value = (response.body() as ArrayList<ResponseClasificacion>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "goles:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val partidosGanadosLocal = MutableStateFlow(arrayListOf<ResponseClasificacion>())
    fun getPartidosGanadosLocal() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getPartidosGanados("local")
            if (response.isSuccessful && response.body() != null) {
                partidosGanadosLocal.value = (response.body() as ArrayList<ResponseClasificacion>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "ganados:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val partidosGanadosVisitante = MutableStateFlow(arrayListOf<ResponseClasificacion>())
    fun getPartidosGanadosVisitante() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getPartidosGanados("visitante")
            if (response.isSuccessful && response.body() != null) {
                partidosGanadosVisitante.value = (response.body() as ArrayList<ResponseClasificacion>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "ganados:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val partidosEmpatadosLocal = MutableStateFlow(arrayListOf<ResponseClasificacion>())
    fun getPartidosEmpatadosLocal() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getPartidosEmpatados("local")
            if (response.isSuccessful && response.body() != null) {
                partidosEmpatadosLocal.value = (response.body() as ArrayList<ResponseClasificacion>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "empatados:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val partidosEmpatadosVisitante = MutableStateFlow(arrayListOf<ResponseClasificacion>())
    fun getPartidosEmpatadosVisitante() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getPartidosEmpatados("visitante")
            if (response.isSuccessful && response.body() != null) {
                partidosEmpatadosVisitante.value = (response.body() as ArrayList<ResponseClasificacion>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "empatados:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val partidosPerdidosLocal = MutableStateFlow(arrayListOf<ResponseClasificacion>())
    fun getPartidosPerdidosLocal() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getPartidosPerdidos("local")
            if (response.isSuccessful && response.body() != null) {
                partidosPerdidosLocal.value = (response.body() as ArrayList<ResponseClasificacion>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "perdidos:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

    val partidosPerdidosVisitante = MutableStateFlow(arrayListOf<ResponseClasificacion>())
    fun getPartidosPerdidosVisitante() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getPartidosPerdidos("visitante")
            if (response.isSuccessful && response.body() != null) {
                partidosPerdidosVisitante.value = (response.body() as ArrayList<ResponseClasificacion>?)!!
            } else {
                Log.i("ClasificacionViewModel" , "perdidos:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }
}