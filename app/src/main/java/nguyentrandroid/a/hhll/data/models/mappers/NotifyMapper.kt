package nguyentrandroid.a.hhll.data.models.mappers

import nguyentrandroid.a.hhll.data.models.entities.ItemNotify
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse

fun NotifyResponse.toListNotify(): List<ItemNotify> {
    var listNotify = arrayListOf<ItemNotify>()
    root?.hits?.hits.let {
        it?.forEach { it ->
            val itemNotify =
                ItemNotify(it._id, it._index, it._source, it._type, it.sort)
            listNotify.add(itemNotify)
        }
    }
    return listNotify
}