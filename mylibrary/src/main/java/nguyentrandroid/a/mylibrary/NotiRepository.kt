package nguyentrandroid.a.mylibrary

import ApiService
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nguyentrandroid.a.mylibrary.api.ApiClient
import nguyentrandroid.a.mylibrary.db.DB
import nguyentrandroid.a.mylibrary.db.NotiDao
import nguyentrandroid.a.mylibrary.modelClass.Data
import nguyentrandroid.a.mylibrary.modelClass.HitDB


class NotiRepository(application: Application) {
    private var apiService: ApiService? = null
    private var notiDao: NotiDao? = null
    init {
        notiDao = DB.getDatabase(application).NotiDao()
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
                { Log.d("AAA","adasdad") }
            )

        return liveData
    }

    fun LoadDataOff() : LiveData<List<HitDB>>? {
        return notiDao?.getAllNoti()
    }



}
