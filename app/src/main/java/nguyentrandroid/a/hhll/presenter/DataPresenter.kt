package nguyentrandroid.a.hhll.presenter

import nguyentrandroid.a.hhll.`interface`.DataInterface
import nguyentrandroid.a.hhll.model.repos.DataRepos
import nguyentrandroid.demohhll.model.Data

class DataPresenter(dataView: DataInterface.DataView) : DataInterface.dataPresenter {


    private var view: DataInterface.DataView = dataView
    private var model: DataInterface.DataModel = DataRepos()
    override fun showData(): Data {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun uiAutoUpdate() {
        view.updateViewData()
    }



}