package nguyentrandroid.a.hhll.view

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nguyentrandroid.a.mylibrary.NotiRepository
import nguyentrandroid.a.mylibrary.modelClass.Data


class MainPresenter(notiRepository: NotiRepository) : ViewModel() {


    val data: LiveData<Data>? = notiRepository?.LoadData()


//    init {
//        val notiDao = DB.getDatabase(context, viewModelScope).NotiDao()
//        repository = NotiRepository(notiDao)
//        allWords = repository.listNoti
//        data = repository.LoadData()
//    }
}
