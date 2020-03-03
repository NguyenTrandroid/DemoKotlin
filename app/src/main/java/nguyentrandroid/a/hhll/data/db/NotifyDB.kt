package nguyentrandroid.a.hhll.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nguyentrandroid.a.hhll.data.models.entities.ItemNotifyDB
import nguyentrandroid.a.mylibrary.utils.DataConverter

@Database(entities = [ItemNotifyDB::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class NotifyDB : RoomDatabase() {
    abstract fun notifyDao(): NotifyDao

    companion object {
        @Volatile
        private var INSTANCE: NotifyDB? = null

        fun getDatabase(context: Context): NotifyDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotifyDB::class.java,
                    "notify_database.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
