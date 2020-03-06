package nguyentrandroid.a.hhll.classes.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}


private fun getErrorMessage(report: PagingRequestHelper.StatusReport): String {
    return PagingRequestHelper.RequestType.values().mapNotNull {
        report.getErrorFor(it)?.message
    }.first()
}

fun PagingRequestHelper.createStatusLiveData(): LiveData<NetworkState> {
    val liveData = MutableLiveData<NetworkState>()
    addListener { report ->
        when {
            report.hasRunning() -> liveData.postValue(NetworkState.LOADING)
            report.hasError() -> liveData.postValue(
                NetworkState.error(getErrorMessage(report)))
            else -> liveData.postValue(NetworkState.LOADED)
        }
    }
    return liveData
}

