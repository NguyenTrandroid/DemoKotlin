package nguyentrandroid.a.hhll.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.services.NotifyService
import java.util.concurrent.Executor

class NotifyDataSourceFactory(var user:String,var scope: CoroutineScope?, val service: NotifyService?,var dao: NotifyDao?,var retryExecutor: Executor?) : DataSource.Factory<String, Hit>() {
    var sourceLiveData = MutableLiveData<NotifyDataSource>()
    override fun create(): DataSource<String, Hit> {
        val source = NotifyDataSource(user,scope,service,dao,retryExecutor)
        sourceLiveData.postValue(source)
        return source
    }
}