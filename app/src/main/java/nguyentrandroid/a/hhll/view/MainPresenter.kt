package nguyentrandroid.a.hhll.view
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nguyentrandroid.a.mylibrary.HomeRepository
import nguyentrandroid.a.mylibrary.modelClass.Data

class MainPresenter(private val homeRepository: HomeRepository) : ViewModel() {
    var _data = MutableLiveData<Data>()

    init {
        getData()
    }

    private fun getData() {
        _data.postValue(homeRepository.LoadData().value)
    }
}