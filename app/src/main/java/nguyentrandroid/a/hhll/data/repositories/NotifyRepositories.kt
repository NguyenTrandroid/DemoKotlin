package nguyentrandroid.a.hhll.data.repositories

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import nguyentrandroid.a.hhll.data.api.API
import nguyentrandroid.a.hhll.data.db.NotifyDB
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.models.entities.ItemNotifyDB
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse
import nguyentrandroid.a.hhll.data.services.NotifyService
import nguyentrandroid.a.mylibrary.modelClass.HitDB
import java.io.File

class NotifyRepositories private constructor() {

    companion object {
        val INSTANCE = NotifyRepositories

        suspend fun insert(dao: NotifyDao, itemNotifyDB: ItemNotifyDB) {
            dao.insert(itemNotifyDB)
        }

        fun getAllNotify(dao: NotifyDao): LiveData<List<ItemNotifyDB>> {
            return dao.getAllNoti()
        }

        suspend fun getNotify(u: String, l: Int): NotifyResponse {
            return API.getClient().create(NotifyService::class.java).getNotify(u, l).await()
        }

        suspend fun getNotifyAfter(u: String, l: Int, a: String): NotifyResponse {
            return API.getClient().create(NotifyService::class.java).getnotifyAfter(u, l, a).await()
        }
    }
}