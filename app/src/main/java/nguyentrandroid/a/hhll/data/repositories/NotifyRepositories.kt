package nguyentrandroid.a.hhll.data.repositories

import nguyentrandroid.a.hhll.data.api.API
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse

class NotifyRepositories  private constructor(){
    companion object{
        val INSTANCE = NotifyRepositories
        suspend fun getNotifyList(u:String,l:Int):NotifyResponse{
            return API.notifyService.getNotify(u,l).await()
        }


    }
}