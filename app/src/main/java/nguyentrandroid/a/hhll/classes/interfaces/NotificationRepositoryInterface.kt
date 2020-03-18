package nguyentrandroid.a.hhll.classes.interfaces

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import nguyentrandroid.a.hhll.classes.utils.Listing
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse

interface NotificationRepositoryInterface {
   suspend fun getData(): NotifyResponse
    fun getListingNotifyOnl(usre: String, scope: CoroutineScope): Listing<Hit>
    fun getDB(): LiveData<List<Hit>>

}