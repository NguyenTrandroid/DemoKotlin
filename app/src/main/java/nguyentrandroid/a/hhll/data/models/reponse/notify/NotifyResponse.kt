package nguyentrandroid.a.hhll.data.models.reponse.notify

import com.google.gson.annotations.SerializedName

data class NotifyResponse (

    @field:SerializedName("took") val took : Int,
    @field:SerializedName("timed_out") val timed_out : Boolean,
    @field:SerializedName("_shards") val _shards : _shards,
    @field:SerializedName("hits") val hits : Hits
)
data class Hits (

    @field:SerializedName("total") val total : Int,
    @field:SerializedName("max_score") val max_score : String,
    @field:SerializedName("hits") val hits : List<Hit>
)
data class Hit(
    @field:SerializedName("_index") val _index : String,
    @field:SerializedName("_type") val _type : String,
    @field:SerializedName("_id") val _id : String,
    @field:SerializedName("_score") val _score : Any,
    @field:SerializedName("_source") val _source : _source,
    @field:SerializedName("sort") val sort : List<Double>
)
data class Fi102 (

    @field:SerializedName("iv109") val iv109 : String,
    @field:SerializedName("targetid") val targetid : String,
    @field:SerializedName("iv102") val iv102 : String,
    @field:SerializedName("iv103") val iv103 : String
)
data class Fi101 (

    @field:SerializedName("iv109") val iv109 : String,
    @field:SerializedName("targetid") val targetid : String,
    @field:SerializedName("iv102") val iv102 : String,
    @field:SerializedName("iv103") val iv103 : String
)
data class _source (
    @field:SerializedName("fi101") val fi101 : List<Fi101>,
    @field:SerializedName("mid_") val mid_ : String,
    @field:SerializedName("iv107") val iv107 : String,
    @field:SerializedName("iv104") val iv104 : String,
    @field:SerializedName("iv105") val iv105 : Int,
    @field:SerializedName("id") val id : String,
    @field:SerializedName("type") val type : String,
    @field:SerializedName("user") val user : String,
    @field:SerializedName("when") val `when` : String,
    @field:SerializedName("fi102") val fi102 : List<Fi102>,
    @field:SerializedName("seen") val seen : Int,
    @field:SerializedName("clicked") val clicked : Int
)
data class _shards (

    @field:SerializedName("total") val total : Int,
    @field:SerializedName("successful") val successful : Int,
    @field:SerializedName("skipped") val skipped : Int,
    @field:SerializedName("failed") val failed : Int
)