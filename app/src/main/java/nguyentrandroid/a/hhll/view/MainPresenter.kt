package nguyentrandroid.a.hhll.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nguyentrandroid.a.hhll.model.repos.HomeRepository
import nguyentrandroid.demohhll.model.Data

class MainPresenter(private val homeRepository: HomeRepository) : ViewModel() {
    var _data = MutableLiveData<Data>()
    init {
        getData()
    }
    private fun getData() {
        _data.postValue(homeRepository.LoadData().value)
    }
}