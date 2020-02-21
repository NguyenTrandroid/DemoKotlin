package nguyentrandroid.a.hhll.view

import nguyentrandroid.a.hhll.model.repos.DataRepository
import nguyentrandroid.demohhll.model.Data

class MainPresenter(private val homeRepository: DataRepository) :
    MainContract.Presenter {
    var homeView: MainContract.View? = null

    override fun getFoods() {
        if (homeView != null) {
            homeView!!.showWait()
        }
        homeRepository.GetData(object : DataRepository.RepositoryCallback<Data> {
            override fun onSuccess(t: Data?) {
                if (homeView != null) {
                    homeView!!.removeWait()
                    homeView!!.showData(t!!)
                }
            }

            override fun onError(error: String) {
                if (homeView != null) {
                    homeView!!.removeWait()
                    homeView!!.onFailure(error)
                }
            }


        })
    }

    override fun attachView(view: MainContract.View) {
        homeView = view
    }

    override fun detachView() {
        homeView = null
    }
}