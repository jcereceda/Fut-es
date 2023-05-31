

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.pfc.API.ApiRest
import com.utad.pfc.model.Noticia
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NoticiasViewModel: ViewModel() {

    val coroutineExceptionHandler : CoroutineExceptionHandler =
        (CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        })


    val noticias = MutableStateFlow(arrayListOf<Noticia>())
    val loading = MutableStateFlow(false)

    fun getNoticias() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler , block = {
            val response = ApiRest.service.getNoticias()
            if (response.isSuccessful && response.body() != null) {
                noticias.value = (response.body() as ArrayList<Noticia>?)!!
            } else {
                Log.i("NOticiasViewModel" , "NOticais:${response.errorBody()?.string() }")
            }
            loading.value = false
        })
    }

}