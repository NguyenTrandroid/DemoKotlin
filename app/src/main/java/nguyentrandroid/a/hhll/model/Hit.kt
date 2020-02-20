package nguyentrandroid.demohhll.model

data class Hit(
    val _id: String,
    val _index: String,
    val _score: Any,
    val _source: Source,
    val _type: String,
    val sort: List<Long>
)