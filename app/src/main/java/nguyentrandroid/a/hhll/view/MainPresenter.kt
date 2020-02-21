package nguyentrandroid.a.hhll.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nguyentrandroid.a.mylibrary.HomeRepository
import nguyentrandroid.a.mylibrary.modelClass.Data

class MainPresenter(private val homeRepository: HomeRepository) : ViewModel() {

    fun getData(): LiveData<Data> {
        return homeRepository.LoadData()
    }

}