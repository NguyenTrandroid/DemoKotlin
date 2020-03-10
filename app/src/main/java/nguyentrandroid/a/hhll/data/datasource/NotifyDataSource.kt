package nguyentrandroid.a.hhll.data.datasource

import android.app.Application
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.classes.utils.Constants
import nguyentrandroid.a.hhll.classes.utils.Constants.Companion.LIST_KEY
import nguyentrandroid.a.hhll.classes.utils.NetworkState
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.services.NotifyService
import java.io.IOException
import java.util.concurrent.Executor

class NotifyDataSource(
    var user: String,
    var scope: CoroutineScope?,
    var service: NotifyService?,
    var dao: NotifyDao?,
    var retryExecutor: Executor?
) : PageKeyedDataSource<String, Hit>() {
    var a = 0
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

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Hit>
    ) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        scope?.launch {
            try {
                val response =
                    service?.getNotify(user, params.requestedLoadSize)
                        ?.await()
                response?.hits?.hits?.let {
                    it.forEach {
                        if (LIST_KEY.containsKey(it._source.iv104)) {
                            when (it._source.iv104) {
                                "noti_friend_mention_user_in_comment",
                                "noti_user_react_post_user_tagged",
                                "halo_noti_share_post",
                                "halo_noti_create_post" -> {
                                    it._source.title =
                                        it._source.fi101[0].iv102 + " " + LIST_KEY[it._source.iv104]
                                }
                                "noti_friend_invite_like" -> {
                                    if (it._source.fi102.lastOrNull()?.iv109.equals("page")) {
                                        it._source.title =
                                            it._source.fi101[0].iv102 + " " + LIST_KEY[it._source.iv104] + " " + it._source.fi102.lastOrNull()?.iv102
                                    }
                                }
                                else -> {
                                    it._source.title =
                                        it._source.fi101[0].iv102 + " " + LIST_KEY[it._source.iv104] + " " + it._source.iv107
                                }
                            }
                        } else {
                            it._source.title = ""
                        }
                    }
                    SaveDataToDB(dao, it)
                    callback.onResult(it, null, makeSort(it.lastOrNull()?.sort))
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)

                }
            } catch (ioException: IOException) {
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
                    user,
                    params.requestedLoadSize,
                    params.key
                )?.await()

                response?.hits?.hits?.let {

                    it.forEach {
                        if (it._source.iv104.equals("noti_friend_invite_like")) {
                            Log.d("AAA", "vo day")
                        }
                        if (LIST_KEY.containsKey(it._source.iv104)) {
                            when (it._source.iv104) {
                                "noti_friend_mention_user_in_comment",
                                "noti_user_react_post_user_tagged",
                                "halo_noti_share_post",
                                "halo_noti_create_post" -> {
                                    it._source.title =
                                        it._source.fi101[0].iv102 + " " + LIST_KEY[it._source.iv104]
                                }
                                "noti_friend_invite_like" -> {
                                    if (it._source.fi102.lastOrNull()?.iv109.equals("page")) {
                                        it._source.title =
                                            it._source.fi101[0].iv102 + " " + LIST_KEY[it._source.iv104] + it._source.fi102.lastOrNull()?.iv102
                                    }
                                }
                                else -> {
                                    it._source.title =
                                        it._source.fi101[0].iv102 + " " + LIST_KEY[it._source.iv104] + " " + it._source.iv107
                                }
                            }
                        } else {
                            it._source.title = ""
                        }
                    }
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

    private suspend fun SaveDataToDB(dao: NotifyDao?, hits: List<Hit>) {
        dao?.deleteAll()
        dao?.insert(hits)
    }
}