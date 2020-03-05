package nguyentrandroid.a.hhll.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import nguyentrandroid.a.hhll.classes.utils.NetworkState
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.services.NotifyService
import java.io.IOException
import java.util.concurrent.Executor

class NotifyDataSource(var scope: CoroutineScope?, var service: NotifyService?, var retryExecutor: Executor?) : PageKeyedDataSource<String, Hit>() {
    private var retry: (() -> Any)? = null
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()
    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor?.execute {
                it.invoke()
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Hit>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        scope?.launch {
            try {
                val response =
                    service?.getNotify("5bd2ec89a7262a092eb062f7", params.requestedLoadSize)
                        ?.await()
                response?.hits?.hits?.let {
                    callback.onResult(it, null, makeSort(it.lastOrNull()?.sort))
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)

                }
            }catch (ioException: IOException){
                retry = {
                    loadInitial(params, callback)
                }
                val error = NetworkState.error(ioException.message ?: "unknown error")
                networkState.postValue(error)
                initialLoad.postValue(error)
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Hit>) {
        networkState.postValue(NetworkState.LOADING)

        scope?.launch {
            try {
                val response = service?.getnotifyAfter(
                    "5bd2ec89a7262a092eb062f7",
                    params.requestedLoadSize,
                    params.key
                )?.await()

                response?.hits?.hits?.let {
                    callback.onResult(it, makeSort(it.lastOrNull()?.sort))
                    networkState.postValue(NetworkState.LOADED)
                }

            } catch (ioException: IOException) {
                retry = { loadAfter(params, callback) }
                networkState.postValue(NetworkState.error(ioException.message ?: "unknown err"))
            }
            }

        }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Hit>) {}

    private fun makeSort(objects: List<Any>?): String {
        return try {
            objects?.run {
                val stringBuilder = StringBuilder("[")
                for (i in objects.indices) {
                    when (val o = objects[i]) {
                        is Long -> {
                            stringBuilder.append((o as Long).toLong())
                        }
                        is Double -> {
                            stringBuilder.append((o as Double).toDouble())
                        }
                        is String -> {
                            stringBuilder.append("\"")
                            stringBuilder.append(o)
                            stringBuilder.append("\"")
                        }
                    }
                    if (i < objects.size - 1) {
                        stringBuilder.append(",")
                    }
                }
                stringBuilder.append("]")
                stringBuilder.toString()
            } ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}
