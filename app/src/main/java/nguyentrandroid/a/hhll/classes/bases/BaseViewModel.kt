package nguyentrandroid.a.hhll.classes.bases

import android.app.Application
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.async

abstract class BaseViewModel : ViewModel() {
    protected val application: Application? = null
    protected val viewModelJob = Job()
    protected val viewModelScope = CoroutineScope(Default + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    protected fun async(error: (throwable: Throwable) -> Unit = {}, call: suspend () -> Unit = {}): Deferred<*> {
        return viewModelScope.async {
            runCatching {
                call()
            }.onFailure {
                it.printStackTrace()
                error(it)
            }
        }
    }




}