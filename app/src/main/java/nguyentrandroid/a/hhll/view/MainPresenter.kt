package nguyentrandroid.a.hhll.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nguyentrandroid.a.hhll.model.repos.HomeRepository
import nguyentrandroid.demohhll.model.Data

class MainPresenter(private val homeRepository: HomeRepository) :
    MainContract.Presenter, ViewModel() {

    private var _data = MutableLiveData<Data>()
    val dataResult: LiveData<Data>
        get() = _data

    override fun getData(live: LiveData<Data>) {
        homeRepository.LoadData { it ->
            _data.postValue(it)
        }

    }


    override fun attachView(view: MainContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun detachView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}