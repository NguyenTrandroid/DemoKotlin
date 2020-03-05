package nguyentrandroid.a.hhll.ui.notify

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import nguyentrandroid.a.hhll.classes.bases.BaseViewModel
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.data.api.API
import nguyentrandroid.a.hhll.data.datasource.NotifyDataSourceFactory
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.repositories.NotifyRepositories
import nguyentrandroid.a.hhll.data.services.NotifyService


class MainViewModel(u: String, l: Int, application: Application) : BaseViewModel(application) {
    //    private val NETWORK_IO = Executors.newFixedThreadPool(5)
//    private var notifyDao: NotifyDao
    private val _repository by lazy { NotifyRepositories.INSTANCE }
//    private val _listNotify = MutableLiveData<List<ItemNotify>>()
//    val listNotify: LiveData<List<ItemNotify>>
//        get() = _listNotify
//    private var dataSourceFactory: NotifyDataSourceFactory


    init {

//        notifyDao = NotifyDB.getDatabase(application).notifyDao()
        getData(u, l)
    }

    private fun getData(u: String, l: Int) {
        async {
        }
    }

//    fun deleteDataDB() = viewModelScope.launch {
//        notifyDao.deleteAll()
//    }

//    fun insert(itemNotifyDB: ItemNotifyDB) = viewModelScope.launch {
//        _repository.insert(notifyDao, itemNotifyDB)
//
//    }
//
//    fun getAllNotify(): LiveData<List<ItemNotifyDB>> {
//        return _repository.getAllNotify(notifyDao)
//    }

    fun getListing(): Listing<Hit> {
        return _repository.getDataListing(viewModelScope)
    }

    fun retry() {
        return _repository.getDataListing(viewModelScope).retry?.invoke()
    }
}

