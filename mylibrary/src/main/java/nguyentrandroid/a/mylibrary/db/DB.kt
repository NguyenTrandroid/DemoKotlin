package nguyentrandroid.a.mylibrary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nguyentrandroid.a.mylibrary.modelClass.HitDB
import nguyentrandroid.a.mylibrary.utils.DataConverter

@Database(entities = [HitDB::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)

abstract class DB : RoomDatabase() {
    abstract fun NotiDao(): NotiDao

    companion object {
        @Volatile
        private var INSTANCE: DB? = null

        fun getDatabase(context: Context): DB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}