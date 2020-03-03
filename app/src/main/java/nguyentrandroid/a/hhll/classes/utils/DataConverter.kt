package nguyentrandroid.a.hhll.classes.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import nguyentrandroid.a.hhll.data.models.entities.source
import nguyentrandroid.a.hhll.data.models.reponse.notify._source

class DataConverter {

        @TypeConverter
        fun sourceToString(source: _source): String = Gson().toJson(source)

        @TypeConverter
        fun stringToSource(string: String): _source = Gson().fromJson(string, _source::class.java)

        @TypeConverter
        fun sortToString(sort: List<Double>): String? {
            return Gson().toJson(sort)
        }

        @TypeConverter
        fun stringToSort(value: String): List<Double>? {

            val objects = Gson().fromJson(value, Array<Double>::class.java) as Array<Double>
            val list = objects.toList()
            return list
        }

}