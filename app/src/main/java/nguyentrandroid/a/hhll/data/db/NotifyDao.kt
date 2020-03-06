package nguyentrandroid.a.hhll.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.Deferred
import nguyentrandroid.a.hhll.data.models.entities.ItemNotifyDB
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse

@Dao
interface NotifyDao {

    @Query("SELECT * from noti_table")
    fun getAllNoti(): DataSource.Factory<Int, Hit>

    @Query("SELECT * from noti_table")
    fun getAllDB(): LiveData<List<Hit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hits: List<Hit>)

    @Query("DELETE FROM noti_table")
    suspend fun deleteAll()


}