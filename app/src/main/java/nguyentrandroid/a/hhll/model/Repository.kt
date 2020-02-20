package nguyentrandroid.a.hhll.model

import android.database.Observable
import nguyentrandroid.demohhll.model.Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Repository {
    @GET("u=5bd2ec89a7262a092eb062f7&l=10")
    fun GetData():Observable<Data>


}
