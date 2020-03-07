package nguyentrandroid.a.hhll.ui.notify

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import nguyentrandroid.a.hhll.classes.bases.BaseViewModel
import nguyentrandroid.a.hhll.classes.utils.Constants
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.classes.utils.NetworkState
import nguyentrandroid.a.hhll.data.db.NotifyDB
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.repositories.NotifyRepositories


class MainViewModel(application: Application, savedStateHandle: SavedStateHandle) :
    BaseViewModel(application) {

    private val dao: NotifyDao
    private val _repository by lazy { NotifyRepositories.INSTANCE }
    init {
        dao = NotifyDB.getDatabase(application).notifyDao()
    }
    private val repoResultDB = savedStateHandle?.getLiveData<String>(Constants.KEY_SAVESTATE)?.map {
        _repository.postsOfNotify(it, viewModelScope, dao)
    }
    val hitsDB = repoResultDB.switchMap { it.pagedList }
    val networkStateDB = repoResultDB.switchMap { it.networkState }
    fun getListingNotifyOnl(user: String): Listing<Hit> = _repository.getListingNotifyOnl(user, viewModelScope, dao)
    fun refresh() {
        repoResultDB.value?.refresh?.invoke()
    }
    fun retryDB() {
        val listing = repoResultDB.value
        listing?.retry?.invoke()
    }
    fun retryOnl(user: String) {
        _repository.getListingNotifyOnl(user, viewModelScope, dao)?.retry?.invoke()
    }

}
