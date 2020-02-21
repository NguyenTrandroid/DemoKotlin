package nguyentrandroid.a.hhll.model.repos

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nguyentrandroid.a.hhll.`interface`.DataInterface
import nguyentrandroid.a.hhll.model.api.ApiClient
import nguyentrandroid.a.hhll.model.api.ApiService
import nguyentrandroid.demohhll.model.Data

class DataRepos : DataInterface.DataModel {
    private var apiclient: ApiService? = null

    init {
        apiclient = ApiClient.client.create(ApiService::class.java)

    }

    @SuppressLint("CheckResult")
    override fun getData(dataResult: Data, presenter: DataInterface.dataPresenter) {
        apiclient?.GetData()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { result -> },
                { error -> Log.d("AAA", "" + error) }
            )
    }


}