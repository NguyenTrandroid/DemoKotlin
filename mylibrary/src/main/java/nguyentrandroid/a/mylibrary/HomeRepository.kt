package nguyentrandroid.a.mylibrary

import ApiService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nguyentrandroid.a.mylibrary.api.ApiClient
import nguyentrandroid.a.mylibrary.modelClass.Data

class HomeRepository {
    private var apiService: ApiService? = null

    init {

        apiService = ApiClient.client.create(ApiService::class.java)
    }

    fun LoadData(): LiveData<Data> {
        var liveData = MutableLiveData<Data>()
        apiService?.GetData()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { result ->
                    Log.d("AAA", result.took.toString())
                    liveData.value = result
                },
                { error -> Log.d("AAA", "" + error) }
            )

        return liveData
    }
}
