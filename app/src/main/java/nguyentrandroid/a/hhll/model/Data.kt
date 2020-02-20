package nguyentrandroid.demohhll.model

data class Data(
    val _shards: Shards,
    val hits: Hits,
    val timed_out: Boolean,
    val took: Int
)