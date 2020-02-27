package nguyentrandroid.a.mylibrary.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nguyentrandroid.a.mylibrary.modelClass.HitDB
import nguyentrandroid.a.mylibrary.utils.DataConverter

@Database(entities = [HitDB::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)

abstract class DB : RoomDatabase() {

}