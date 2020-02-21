package nguyentrandroid.a.hhll.view

import nguyentrandroid.demohhll.model.Data

interface MainContract {
    interface View  {
        fun showWait()
        fun removeWait()
        fun showData(data: Data)
        fun onFailure(message: String)
    }

    interface Presenter{
        fun getFoods()
        fun attachView(view: View)
        fun detachView()
    }
}