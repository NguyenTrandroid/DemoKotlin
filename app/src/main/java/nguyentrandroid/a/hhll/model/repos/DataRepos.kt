package nguyentrandroid.a.hhll.model.repos

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nguyentrandroid.a.hhll.`interface`.DataInterface
import nguyentrandroid.a.hhll.model.api.ApiClient
import nguyentrandroid.a.hhll.model.api.ApiService
import nguyentrandroid.demohhll.model.Data
import javax.security.auth.callback.Callback


class DataRepository private constructor() {
    private var apiService: ApiService? = null

    init {
        apiService = ApiClient.client.create(ApiService::class.java)
    }

    fun GetData(callback: RepositoryCallback<Data>) {
        apiService?.GetData()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { result -> callback.onSuccess(result) },
                { error -> callback.onError(error.toString()) }
            )
    }

    interface RepositoryCallback<in T> {
        fun onSuccess(t: T?)
        fun onError(error: String)
    }
}