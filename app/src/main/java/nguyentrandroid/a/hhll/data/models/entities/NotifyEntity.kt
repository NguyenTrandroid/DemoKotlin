package nguyentrandroid.a.hhll.data.models.entities

import nguyentrandroid.a.hhll.data.models.reponse.notify._source


data class ItemNotify(
    val _id: String,
    val _index: String,
    val _source: _source,
    val _type: String,
    val sort: List<Double>

)
data class Source(
    val fi101: List<Fi101>,
    val fi102: List<Fi102>,
    val id: String,
    val iv104: String,
    val mid_: String,
    val seen: Int,
    val type: String,
    val user: String,
    val `when`: String,
    val iv107:String
)
data class Fi101(
    val iv102: String,
    val iv103: String,
    val iv109: String,
    val targetid: String
)
data class Fi102(
    val iv102: String,
    val iv103: String,
    val iv109: String,
    val iv110: String,
    val targetid: String
)