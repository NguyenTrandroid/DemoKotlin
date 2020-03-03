package nguyentrandroid.a.hhll.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.Deferred
import nguyentrandroid.a.hhll.data.models.entities.ItemNotifyDB
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse

@Dao
interface NotifyDao {

    @Query("SELECT * from noti_table")
    fun getAllNoti(): LiveData<List<ItemNotifyDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(itemNotifyDB: ItemNotifyDB)

    @Query("DELETE FROM noti_table")
    suspend fun deleteAll()


}