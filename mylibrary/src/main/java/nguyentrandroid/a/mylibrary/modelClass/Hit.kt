package nguyentrandroid.a.mylibrary.modelClass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import nguyentrandroid.a.mylibrary.utils.DataConverter

data class Hit(
    val _id: String,
    val _index: String,
    val _score: Any,
    val _source: Source,
    val _type: String,
    val sort: List<Double>

)

@Entity(tableName = "noti_table")
data class HitDB(

    @PrimaryKey @ColumnInfo(name = "id") val _id: String,
    @ColumnInfo(name = "index") val _index: String,
    @ColumnInfo(name = "type") val _type: String,

    @TypeConverters(DataConverter::class)
    @ColumnInfo(name = "sort") val sort: List<Double>,

    @TypeConverters(DataConverter::class)
    @ColumnInfo(name = "source") val source: Source
)