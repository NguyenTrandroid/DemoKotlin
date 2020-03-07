package nguyentrandroid.a.hhll.data.db

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nguyentrandroid.a.hhll.classes.utils.NetworkState
import nguyentrandroid.a.hhll.classes.utils.PagingRequestHelper
import nguyentrandroid.a.hhll.classes.utils.createStatusLiveData
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.services.NotifyService
import java.util.concurrent.Executor
import kotlin.reflect.KFunction2

class NotifyBoundaryCallback(
    private val user: String,
    private val pageSize: Int,
    private val scope: CoroutineScope,
    private val dao: NotifyDao,
    private val notifyService: NotifyService,
    private val ioExecutor: Executor,
    private val handleResponse: (NotifyDao, List<Hit>) -> Unit
) : PagedList.BoundaryCallback<Hit>() {

    val helper = PagingRequestHelper(ioExecutor)
    val networkState = helper.createStatusLiveData()

    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            scope.launch {
                try {
                    withContext(Dispatchers.Default) {
                        val response = notifyService.getNotify(user, pageSize).await()
                        insertItemsIntoDb(response.hits.hits, it)
                    }
                } catch (t: Throwable) {
                    scope.launch {
                        withContext(Dispatchers.Default) {
                            it.recordFailure(t)
                        }
                    }
                }
            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Hit) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            scope.launch {
                try {
                    withContext(Dispatchers.Default) {
                        val response =
                            notifyService.getnotifyAfter(user, pageSize, makeSort(itemAtEnd.sort))
                                .await()
                        insertItemsIntoDb(response.hits.hits, it)
                    }
                } catch (t: Throwable) {
                    scope.launch {
                        withContext(Dispatchers.Default) {
                            it.recordFailure(t)
                        }
                    }
                }
            }
        }
    }

    private fun insertItemsIntoDb(response: List<Hit>, it: PagingRequestHelper.Request.Callback) {
        ioExecutor.execute {
            handleResponse(dao, response)
            it.recordSuccess()
        }
    }

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
