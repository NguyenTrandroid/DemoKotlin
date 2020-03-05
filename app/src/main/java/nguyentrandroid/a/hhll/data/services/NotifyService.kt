package nguyentrandroid.a.hhll.data.services

import io.reactivex.Observer
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NotifyService {

    @GET("/notis?t=information,order,order_n_busi,wallet,business,info_n_busi,info_n_wallet,busi_n_wallet,order_n_wallet,info_n_busi_n_wallet,order_n_busi_n_wallet,noti_user_react_tour,noti_user_react_shop,noti_user_react_hotel,noti_user_react_page")
     fun getNotify(
        @Query("u") u: String? =null,
        @Query("l") l: Int? = null
    ): Deferred<NotifyResponse>

    @GET("/notis?t=information,order,order_n_busi,wallet,business,info_n_busi,info_n_wallet,busi_n_wallet,order_n_wallet,info_n_busi_n_wallet,order_n_busi_n_wallet,noti_user_react_tour,noti_user_react_shop,noti_user_react_hotel,noti_user_react_page")
     fun getnotifyAfter(
        @Query("u") u: String? =null,
        @Query("l") l: Int? = null,
        @Query("a") a: String? = null
    ): Deferred<NotifyResponse>




}