package nguyentrandroid.a.hhll.`interface`

import nguyentrandroid.demohhll.model.Data

interface DataInterface {

    interface DataModel {
        fun getData( dataResult:Data,presenter: DataInterface.dataPresenter)


    }

    interface DataView {
        fun updateViewData()

    }

    interface dataPresenter {
        fun showData() : Data
        fun uiAutoUpdate()
    }
}