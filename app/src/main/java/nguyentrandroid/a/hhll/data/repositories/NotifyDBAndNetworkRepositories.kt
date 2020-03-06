package nguyentrandroid.a.hhll.data.repositories

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.paging.toLiveData
import kotlinx.coroutines.*
import nguyentrandroid.a.hhll.classes.utils.Constants.Companion.DEFAULT_NETWORK_PAGE_SIZE
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.classes.utils.NetworkState
import nguyentrandroid.a.hhll.data.api.API
import nguyentrandroid.a.hhll.data.db.NotifyBoundaryCallback
import nguyentrandroid.a.hhll.data.db.NotifyDB
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.services.NotifyService
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class NotifyDBAndNetworkRepositories() {
    companion object {
        lateinit var scope: CoroutineScope
        lateinit var dao: NotifyDao
        val notifyService: NotifyService = API.getClient().create(NotifyService::class.java)
        lateinit var ioExecutor:Executor
        val networkPageSize: Int = DEFAULT_NETWORK_PAGE_SIZE
        val INSTANCE = NotifyDBAndNetworkRepositories

        private fun insertResultIntoDb(body: List<Hit>) {
            scope.launch {
                withContext(Dispatchers.Default) {
                    dao.insert(body)
                }
            }
        }

        @MainThread
        private fun refresh(used: String): LiveData<NetworkState> {
            val networkState = MutableLiveData<NetworkState>()
            networkState.value = NetworkState.LOADING
            scope.launch {
                try {
                    val response = notifyService.getNotify(used, networkPageSize).await()
                    ioExecutor?.execute {
                        insertResultIntoDb(response.hits.hits)
                    }
                    networkState.postValue(NetworkState.LOADED)
                } catch (t: Throwable) {
                    networkState.value = NetworkState.error(t.message)
                }

            }
            return networkState
        }

        @MainThread
        fun postsOfNotify(user: String): Listing<Hit> {
            val boundaryCallback = NotifyBoundaryCallback(
                user = user,
                pageSize = networkPageSize,
                scope = scope,
                dao = dao,
                notifyService = notifyService,
                ioExecutor = ioExecutor,
                handleResponse = this::insertResultIntoDb
            )
            val refreshTrigger = MutableLiveData<Unit>()
            val refreshState = refreshTrigger.switchMap {
                refresh(user)
            }
            val livePagedList = dao.getAllNoti().toLiveData(
                pageSize = networkPageSize,
                boundaryCallback = boundaryCallback
            )

            return Listing(
                pagedList = livePagedList,
                networkState = boundaryCallback.networkState ,
                retry = {
                    boundaryCallback.helper.retryAllFailed()
                },
                refresh = {
                    refreshTrigger.value = null
                },
                refreshState = refreshState
            )
        }
    }
}