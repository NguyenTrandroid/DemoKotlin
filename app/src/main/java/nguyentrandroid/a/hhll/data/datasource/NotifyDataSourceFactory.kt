package nguyentrandroid.a.hhll.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.services.NotifyService
import java.util.concurrent.Executor
//
//class NotifyDataSourceFactory(var scope: CoroutineScope?, val service: NotifyService?,var retryExecutor: Executor?) : DataSource.Factory<String, Hit>() {
//    var sourceLiveData = MutableLiveData<NotifyDataSource>()
//    override fun create(): DataSource<String, Hit> {
//        val source = NotifyDataSource(scope,service,retryExecutor)
//        sourceLiveData.postValue(source)
//        return source
//    }
//}