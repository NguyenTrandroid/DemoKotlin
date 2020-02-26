package nguyentrandroid.a.hhll.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nguyentrandroid.a.mylibrary.NotiRepository
import nguyentrandroid.a.mylibrary.modelClass.Data

class MainPresenter(private val homeRepository: NotiRepository) : ViewModel() {

    fun getData(): LiveData<Data> {
        return homeRepository.LoadData()
    }

}