package nguyentrandroid.a.hhll.data.repositories

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nguyentrandroid.a.hhll.classes.utils.Constants.Companion.DEFAULT_NETWORK_PAGE_SIZE
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.classes.utils.NetworkState
import nguyentrandroid.a.hhll.data.api.API
import nguyentrandroid.a.hhll.data.datasource.NotifyDataSourceFactory
import nguyentrandroid.a.hhll.data.db.NotifyBoundaryCallback
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse
import nguyentrandroid.a.hhll.data.services.NotifyService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Singleton
class NotifyRepositories(private val notifyService: NotifyService) {
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    suspend fun getData(): NotifyResponse {
        return notifyService.getNotify("5bd2ec89a7262a092eb062f7", 10).await()
    }

    fun getListingNotifyOnl(usre: String, scope: CoroutineScope): Listing<Hit> {
        val factoty = NotifyDataSourceFactory(usre, scope, notifyService, null, NETWORK_IO)
        var pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(DEFAULT_NETWORK_PAGE_SIZE)
            .setPageSize(DEFAULT_NETWORK_PAGE_SIZE)
        val livePagedList = LivePagedListBuilder<String, Hit>(factoty, pagedListConfig.build())
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
        val refreshState = factoty.sourceLiveData.switchMap {
            it.initialLoad
        }
        return Listing(
            pagedList = livePagedList,
            networkState = factoty.sourceLiveData.switchMap {
                it.networkState
            },
            retry = {
                factoty.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {
                factoty.sourceLiveData.value?.invalidate()
            },
            refreshState = refreshState
        )
    }
}


//    companion object {
//
//        private val notifyService = API.getClient().create(NotifyService::class.java)
//        private val NETWORK_IO = Executors.newFixedThreadPool(5)
//        private var factoty = NotifyDataSourceFactory("", null, notifyService, null, NETWORK_IO)
//        val networkPageSize: Int = DEFAULT_NETWORK_PAGE_SIZE
//        private val ioExecutor = Executors.newSingleThreadExecutor()
//        val INSTANCE = NotifyRepositories
//
//
//        private fun insertResultIntoDb(dao: NotifyDao, body: List<Hit>) {
//            dao.insert(body)
//        }
//
//        @MainThread
//        private fun refresh(
//            scope: CoroutineScope,
//            dao: NotifyDao,
//            used: String
//        ): LiveData<NetworkState> {
//            val networkState = MutableLiveData<NetworkState>()
//            networkState.value = NetworkState.LOADING
//            scope.launch {
//                try {
//                    val response = notifyService.getNotify(
//                        used,
//                        networkPageSize
//                    ).await()
//                    ioExecutor?.execute {
//                        insertResultIntoDb(dao, response.hits.hits)
//                    }
//                    networkState.postValue(NetworkState.LOADED)
//                } catch (t: Throwable) {
//                    networkState.value = NetworkState.error(t.message)
//                }
//
//            }
//            return networkState
//        }
//
//        @MainThread
//        fun postsOfNotify(user: String, scope: CoroutineScope, dao: NotifyDao): Listing<Hit> {
//            val boundaryCallback = NotifyBoundaryCallback(
//                user = user,
//                pageSize = networkPageSize,
//                scope = scope,
//                dao = dao,
//                notifyService = notifyService,
//                ioExecutor = ioExecutor,
//                handleResponse = this::insertResultIntoDb
//            )
//            val refreshTrigger = MutableLiveData<Unit>()
//            val refreshState = refreshTrigger.switchMap {
//                refresh(scope, dao, user)
//            }
//            val livePagedList = dao.getAllNoti().toLiveData(
//                pageSize = networkPageSize,
//                boundaryCallback = boundaryCallback
//            )
//            return Listing(
//                pagedList = livePagedList,
//                networkState = boundaryCallback.networkState,
//                retry = {
//                    boundaryCallback.helper.retryAllFailed()
//                },
//                refresh = {
//                    refreshTrigger.value = null
//                },
//                refreshState = refreshState
//            )
//        }
//
//        fun getDB(dao: NotifyDao): LiveData<List<Hit>> = dao.getAllDB()
//
