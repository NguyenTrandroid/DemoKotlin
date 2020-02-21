package nguyentrandroid.a.hhll.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nguyentrandroid.a.hhll.model.repos.HomeRepository
import nguyentrandroid.demohhll.model.Data

class MainPresenter(private val homeRepository: HomeRepository) :
    MainContract.Presenter, ViewModel() {

     var _data = MutableLiveData<Data>()


    override fun getData() {
        _data.postValue(homeRepository.LoadData().value)
    }

    override fun attachView(view: MainContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun detachView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}