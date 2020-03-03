package nguyentrandroid.a.hhll.data.models.mappers

import android.util.Log
import nguyentrandroid.a.hhll.data.models.entities.ItemNotify
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse

fun NotifyResponse.toListNotify(): List<ItemNotify> {
    //Log.d("AAA",took.toString()+"")
    var listNotify = arrayListOf<ItemNotify>()
    hits?.hits.let {
        it?.forEach { it ->
            val itemNotify =
                ItemNotify(it._id, it._index, it._source, it._type, it.sort)
            listNotify.add(itemNotify)
        }
    }
    return listNotify
}

