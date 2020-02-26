package nguyentrandroid.a.mylibrary

import ApiService
import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nguyentrandroid.a.mylibrary.api.ApiClient
import nguyentrandroid.a.mylibrary.modelClass.Data

class NotiRepository {
    private var apiService: ApiService? = null

    init {

        apiService = ApiClient.getClient().create(ApiService::class.java)
    }

    @SuppressLint("CheckResult")
    fun LoadData(): LiveData<Data> {
        apiService = ApiClient.getClient().create(ApiService::class.java)
        var liveData = MutableLiveData<Data>()
        apiService?.GetData()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { result ->
                    Log.d("AAA", result.took.toString())
                    liveData.value = result
                },
                { error -> liveData.postValue(null) }
            )

        return liveData
    }
}
