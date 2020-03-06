//package nguyentrandroid.a.hhll.data.repositories
//
//import android.util.Log
//import androidx.annotation.MainThread
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.switchMap
//import androidx.paging.toLiveData
//import kotlinx.coroutines.*
//import nguyentrandroid.a.hhll.classes.utils.Constants.Companion.DEFAULT_NETWORK_PAGE_SIZE
//import nguyentrandroid.a.hhll.classes.utils.Listing
//import nguyentrandroid.a.hhll.classes.utils.NetworkState
//import nguyentrandroid.a.hhll.data.api.API
//import nguyentrandroid.a.hhll.data.db.NotifyBoundaryCallback
//import nguyentrandroid.a.hhll.data.db.NotifyDB
//import nguyentrandroid.a.hhll.data.db.NotifyDao
//import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
//import nguyentrandroid.a.hhll.data.services.NotifyService
//import java.util.concurrent.Executor
//import java.util.concurrent.Executors
//
//class NotifyDBAndNetworkRepositories() {
//    companion object {
//        lateinit var scope: CoroutineScope
//        lateinit var dao: NotifyDao
//        val notifyService: NotifyService = API.getClient().create(NotifyService::class.java)
//        lateinit var ioExecutor:Executor
//        val networkPageSize: Int = DEFAULT_NETWORK_PAGE_SIZE
//        val INSTANCE = NotifyDBAndNetworkRepositories
//
//
//
//
//
//    }
//}