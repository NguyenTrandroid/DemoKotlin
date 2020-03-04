package nguyentrandroid.a.hhll.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.data.api.API
import nguyentrandroid.a.hhll.data.datasource.NotifyDataSourceFactory
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.entities.ItemNotifyDB
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse
import nguyentrandroid.a.hhll.data.services.NotifyService
import java.util.concurrent.Executor


class NotifyRepositories private constructor() {

    companion object {
        val factoty = NotifyDataSourceFactory(
            null,
            API.getClient().create(NotifyService::class.java),
            null,
            null
        )
        val INSTANCE = NotifyRepositories
//        suspend fun insert(dao: NotifyDao, itemNotifyDB: ItemNotifyDB) {
//            dao.insert(itemNotifyDB)
//        }
//
//        fun getAllNotify(dao: NotifyDao): LiveData<List<ItemNotifyDB>> {
//            return dao.getAllNoti()
//        }
//
//        suspend fun getNotify(u: String, l: Int): NotifyResponse {
//            return API.getClient().create(NotifyService::class.java).getNotify(u, l).await()
//        }

        fun getDataListing(
            scope: CoroutineScope,
            userId: String,
            pageSize: Int,
            networkExecutor: Executor
        ): Listing<Hit> {
            factoty.userId = userId
            factoty.scope = scope
            factoty.retryExecutor = networkExecutor
            val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(pageSize)
                .setPageSize(pageSize * 2).build()

            val livePagedList =
                LivePagedListBuilder<String, Hit>(factoty, pagedListConfig)
                    .setFetchExecutor(networkExecutor)
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
}