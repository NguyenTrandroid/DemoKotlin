package nguyentrandroid.a.hhll.data.services

import kotlinx.coroutines.Deferred
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse
import nguyentrandroid.a.mylibrary.modelClass.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface NotifyService {

    @GET("notis?t=information,order,order_n_busi,wallet,business,info_n_busi,info_n_wallet,busi_n_wallet,order_n_wallet,info_n_busi_n_wallet,order_n_busi_n_wallet,noti_user_react_tour,noti_user_react_shop,noti_user_react_hotel,noti_user_react_page&u=5bd2ec89a7262a092eb062f7&l=20")
    fun getNotify(@Query("u") u : String?, @Query("l") l : Int): Deferred<NotifyResponse>
}