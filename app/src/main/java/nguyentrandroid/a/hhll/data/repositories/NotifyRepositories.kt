package nguyentrandroid.a.hhll.data.repositories

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import nguyentrandroid.a.hhll.classes.interfaces.NotificationRepositoryInterface
import nguyentrandroid.a.hhll.classes.utils.Constants.Companion.DEFAULT_NETWORK_PAGE_SIZE
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.data.datasource.NotifyDataSourceFactory
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse
import nguyentrandroid.a.hhll.data.services.NotifyService
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class NotifyRepositories @Inject constructor(private val notifyService: NotifyService, private val dao: NotifyDao) : NotificationRepositoryInterface {

    override suspend fun getData(): NotifyResponse {
        return notifyService.getNotify("5bd2ec89a7262a092eb062f7", 10).await()
    }

    @MainThread
    override fun getListingNotifyOnl(usre: String, scope: CoroutineScope,networkExecutor: Executor?): Listing<Hit> {
        val factoty = NotifyDataSourceFactory(usre, scope, notifyService, dao, networkExecutor)
        var pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(DEFAULT_NETWORK_PAGE_SIZE)
            .setPageSize(DEFAULT_NETWORK_PAGE_SIZE)
        val livePagedList = LivePagedListBuilder<String, Hit>(factoty, pagedListConfig.build())
            .setFetchExecutor(networkExecutor!!)
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

    override fun getDB(): LiveData<List<Hit>> {
        return dao.getAllDB()
    }
}



