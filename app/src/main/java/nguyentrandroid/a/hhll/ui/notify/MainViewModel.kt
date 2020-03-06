package nguyentrandroid.a.hhll.ui.notify

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import nguyentrandroid.a.hhll.classes.bases.BaseViewModel
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.data.api.API
import nguyentrandroid.a.hhll.data.db.NotifyDB
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.repositories.NotifyDBAndNetworkRepositories
import nguyentrandroid.a.hhll.data.services.NotifyService


class MainViewModel(u: String, l: Int, application: Application) : BaseViewModel(application) {
    //    private val NETWORK_IO = Executors.newFixedThreadPool(5)
//    private var notifyDao: NotifyDao
    private val _repository by lazy { NotifyDBAndNetworkRepositories.INSTANCE }
    var user: String
//    private val _listNotify = MutableLiveData<List<ItemNotify>>()
//    val listNotify: LiveData<List<ItemNotify>>
//        get() = _listNotify
//    private var dataSourceFactory: NotifyDataSourceFactory


    init {
        _repository.scope = viewModelScope
        _repository.dao = NotifyDB.getDatabase(application).notifyDao()
        user = u

    }

    private fun getData(u: String, l: Int) {
        async {
        }
    }
//    fun getAll():LiveData<List<Hit>>{
//        return _repository.getALl()
//    }
    fun getListing(): Listing<Hit> {
        return _repository.postsOfNotify(user)
    }
}

