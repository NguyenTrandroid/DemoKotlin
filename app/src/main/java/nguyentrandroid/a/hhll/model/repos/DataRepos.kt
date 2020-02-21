package nguyentrandroid.a.hhll.model.repos
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nguyentrandroid.a.hhll.model.api.ApiClient
import nguyentrandroid.a.hhll.model.api.ApiService
import nguyentrandroid.demohhll.model.Data


class HomeRepository {
    private var apiService: ApiService? = null


    init {
        apiService = ApiClient.client.create(ApiService::class.java)
    }

    fun LoadData() : LiveData<Data> {
        var liveData = MutableLiveData<Data?>()
        liveData.postValue(null)
        apiService?.GetData()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { result -> liveData.postValue(result) },
                { error ->  }
            )
        return liveData.value as LiveData<Data>
    }

}