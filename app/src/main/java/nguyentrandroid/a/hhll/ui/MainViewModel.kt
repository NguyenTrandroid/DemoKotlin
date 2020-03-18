package nguyentrandroid.a.hhll.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import nguyentrandroid.a.hhll.classes.bases.BaseViewModel
import nguyentrandroid.a.hhll.classes.interfaces.NotificationRepositoryInterface
import nguyentrandroid.a.hhll.classes.utils.Constants
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse
import nguyentrandroid.a.hhll.data.repositories.NotifyRepositories
import nguyentrandroid.a.hhll.data.services.NotifyService
import nguyentrandroid.a.hhll.di.factory.AssistedSavedStateViewModelFactory
import java.util.concurrent.Executors


class MainViewModel @AssistedInject constructor(
    application: Application,
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val notifyRepositories: NotificationRepositoryInterface
) : BaseViewModel(application) {
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }

    private var user: String? = null
    private val _data = MutableLiveData<NotifyResponse>()
    val data: LiveData<NotifyResponse>
        get() = _data


    init {

        user = savedStateHandle.get(Constants.KEY_SAVESTATE)
    }

    private val repoResul = savedStateHandle?.getLiveData<String>(Constants.KEY_SAVESTATE)?.map {
        notifyRepositories.getListingNotifyOnl(it, viewModelScope, NETWORK_IO)
    }
    val networkState = repoResul.switchMap { it.networkState }
    val listingHit = repoResul.switchMap { it.pagedList }
    fun retry() {
        val listing = repoResul.value
        listing?.retry?.invoke()
    }


}
