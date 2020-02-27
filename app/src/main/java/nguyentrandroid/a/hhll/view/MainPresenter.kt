package nguyentrandroid.a.hhll.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nguyentrandroid.a.mylibrary.NotiRepository
import nguyentrandroid.a.mylibrary.modelClass.Data

class MainPresenter(homeRepository: NotiRepository) : ViewModel() {
    val str:String="adsadad"

    val data:LiveData<Data>?=homeRepository.LoadData()


//    fun getData(): LiveData<Data> {
//        data = homeRepository.LoadData()
//        return homeRepository.LoadData()
//    }

}