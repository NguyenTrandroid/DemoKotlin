package nguyentrandroid.a.hhll.ui.notify

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nguyentrandroid.a.hhll.classes.bases.BaseViewModel
import nguyentrandroid.a.hhll.data.models.entities.ItemNotify
import nguyentrandroid.a.hhll.data.models.mappers.toListNotify
import nguyentrandroid.a.hhll.data.repositories.NotifyRepositories
import nguyentrandroid.a.mylibrary.NotiRepository
import nguyentrandroid.a.mylibrary.modelClass.Data


class MainViewModel(u:String,l:Int) : BaseViewModel() {

    private val _repository by lazy { NotifyRepositories.INSTANCE }
    private val _listNotify = MutableLiveData<List<ItemNotify>>()
    val listNotify: LiveData<List<ItemNotify>>
        get() = _listNotify
    init {
        getData(u,l)
    }


    private fun getData(u:String,l:Int){
        async {
            val notifyReponse = _repository.getNotifyList(u,l)
            _listNotify.postValue(notifyReponse.toListNotify())

        }
    }
}

