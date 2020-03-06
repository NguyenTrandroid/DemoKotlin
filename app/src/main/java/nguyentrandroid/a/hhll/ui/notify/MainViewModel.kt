package nguyentrandroid.a.hhll.ui.notify

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import nguyentrandroid.a.hhll.classes.bases.BaseViewModel
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.classes.utils.NetworkState
import nguyentrandroid.a.hhll.data.api.API
import nguyentrandroid.a.hhll.data.datasource.NotifyDataSourceFactory
import nguyentrandroid.a.hhll.data.db.NotifyDB
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.repositories.NotifyRepositories
import nguyentrandroid.a.hhll.data.services.NotifyService


class MainViewModel(application: Application) : BaseViewModel(application) {

    private val dao: NotifyDao
    private val _repository by lazy { NotifyRepositories.INSTANCE }
    private val networkState = MutableLiveData<NetworkState>()


    init {
        dao = NotifyDB.getDatabase(application).notifyDao()
    }

    fun getListingNotifyOnl(user: String): Listing<Hit> {
        return _repository.getListingNotifyOnl(user, viewModelScope, dao)
    }

    fun getListingNotifyDB(user: String): Listing<Hit> {
        return _repository.postsOfNotify(user, viewModelScope, dao,networkState)
    }


    fun getDB(): LiveData<List<Hit>> {
        return _repository.getDB(dao)
    }

    fun retry(user: String) {
        return _repository.getListingNotifyOnl(user, viewModelScope, dao).retry?.invoke()
    }


}
