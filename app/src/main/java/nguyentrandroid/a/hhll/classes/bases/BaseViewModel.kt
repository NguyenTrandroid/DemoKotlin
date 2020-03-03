package nguyentrandroid.a.hhll.classes.bases

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.async
import nguyentrandroid.a.hhll.data.db.NotifyDB
import nguyentrandroid.a.hhll.data.db.NotifyDao

abstract class BaseViewModel(application: Application) : ViewModel(), BasicState {
    protected val viewModelJob = Job()
    protected val viewModelScope = CoroutineScope(Default + viewModelJob)
    val globalState = SingleLiveEvent<GlobalState>()
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    protected fun async(
        error: (throwable: Throwable) -> Unit = {},
        call: suspend () -> Unit = {}
    ): Deferred<*> {
        return viewModelScope.async {
            runCatching {
                showLoading()
                call()
            }.onFailure {
                it.printStackTrace()
                error(it)
                postError(it.message)
            }.onSuccess {
                hideLoading()
            }
        }
    }

    override fun postError(msg: String?) {
        globalState.postValue(GlobalState.ERROR.apply {
            message = msg ?: ""
        })
    }

    override fun showLoading() {
        globalState.postValue(GlobalState.SHOW_LOADING)
    }

    override fun hideLoading() {
        globalState.postValue(GlobalState.HIDE_LOADING)
    }
}

interface BasicState {
    fun postError(msg: String?)
    fun showLoading()
    fun hideLoading()
}

enum class GlobalState {
    SHOW_LOADING,
    HIDE_LOADING,
    ERROR;
    var id: Int = -1
    var message: String = ""
}
