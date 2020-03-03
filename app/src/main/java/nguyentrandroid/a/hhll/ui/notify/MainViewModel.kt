package nguyentrandroid.a.hhll.ui.notify

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import nguyentrandroid.a.hhll.classes.bases.BaseViewModel
import nguyentrandroid.a.hhll.data.db.NotifyDB
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.entities.ItemNotify
import nguyentrandroid.a.hhll.data.models.entities.ItemNotifyDB
import nguyentrandroid.a.hhll.data.models.mappers.toListNotify
import nguyentrandroid.a.hhll.data.repositories.NotifyRepositories


class MainViewModel(u: String, l: Int, application: Application) : BaseViewModel(application) {
    private var notifyDao: NotifyDao
    private val _repository by lazy { NotifyRepositories.INSTANCE }
    private val _listNotify = MutableLiveData<List<ItemNotify>>()
    val listNotify: LiveData<List<ItemNotify>>
        get() = _listNotify

    init {
        notifyDao = NotifyDB.getDatabase(application).notifyDao()
        getData(u, l)


    }

    private fun getData(u: String, l: Int) {
        async {
            val notifyReponse = _repository.getNotify(u, l)
            _listNotify.postValue(notifyReponse.toListNotify())
        }
    }

    fun deleteDataDB() = viewModelScope.launch {
        notifyDao.deleteAll()
    }

    fun insert(itemNotifyDB: ItemNotifyDB) = viewModelScope.launch {
        _repository.insert(notifyDao, itemNotifyDB)

    }

    fun getAllNotify(): LiveData<List<ItemNotifyDB>> {
        return _repository.getAllNotify(notifyDao)
    }

}

