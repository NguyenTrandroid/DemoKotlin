package nguyentrandroid.a.mylibrary.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import nguyentrandroid.a.mylibrary.modelClass.HitDB

@Dao
interface NotiDao{

    @Query("SELECT * from noti_table")
    fun getAllNoti(): LiveData<List<HitDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNoti(hitDB: HitDB)


}