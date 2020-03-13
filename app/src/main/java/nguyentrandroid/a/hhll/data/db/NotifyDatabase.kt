package nguyentrandroid.a.hhll.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit

@Database(entities = [Hit::class], version = 1, exportSchema = false)
abstract class NotifyDatabase : RoomDatabase() {

    abstract fun dao(): NotifyDao
}