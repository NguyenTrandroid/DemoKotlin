package nguyentrandroid.a.hhll.ui.notify

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import nguyentrandroid.a.hhll.classes.bases.BaseViewModel
import nguyentrandroid.a.hhll.classes.utils.Constants
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.classes.utils.NetworkState
import nguyentrandroid.a.hhll.data.db.NotifyDB
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse
import nguyentrandroid.a.hhll.data.repositories.NotifyRepositories
import nguyentrandroid.a.hhll.data.services.NotifyService
import nguyentrandroid.a.hhll.di.factory.AssistedSavedStateViewModelFactory


class MainViewModel @AssistedInject constructor(
    application: Application, @Assisted private val savedStateHandle: SavedStateHandle,
    notifyService: NotifyService
) :
    BaseViewModel(application) {
    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }

    private var user: String? = null
    private val notifyRepositories = NotifyRepositories(notifyService)
    private val _data = MutableLiveData<NotifyResponse>()
    val data: LiveData<NotifyResponse>
        get() = _data


    init {
        user = savedStateHandle.get(Constants.KEY_SAVESTATE)
        getData()
    }

    fun getListingNotifyOnl(): Listing<Hit> =
        notifyRepositories.getListingNotifyOnl(user ?: "", viewModelScope)

    private fun getData() {
        async {
            _data.postValue(notifyRepositories.getData())

        }
    }


}
