package com.utad.pfc.ui.estadisticasNoticias.noticias.leer_noticia

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.pfc.API.ApiRest
import com.utad.pfc.model.Comentario
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LeerNoticiaViewModel : ViewModel() {

    val comentarios = MutableStateFlow(arrayListOf<Comentario>())
    fun getComentarios(id_noticia: Int) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            val response =
                ApiRest.service.getComentariosPorNoticia(ApiRest.UserLogged.accessToken, id_noticia)
            if (response.isSuccessful && response.body() != null) {
                comentarios.value = (response.body() as ArrayList<Comentario>?)!!
            } else {
                Log.i("LeerNoticiaVM", "Comentarios:${response.errorBody()?.string()}")
            }
        })
    }

    fun subirComentario(comentario: Comentario) {
        val coroutineExceptionHandler: CoroutineExceptionHandler =
            (CoroutineExceptionHandler { _, throwable ->
                throwable.printStackTrace()
            })

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler, block = {
            ApiRest.service.postComentario(ApiRest.UserLogged.accessToken, comentario)
            getComentarios(comentario.id_noticia)
        })
    }

}