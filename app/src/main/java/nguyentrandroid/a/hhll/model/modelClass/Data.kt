package nguyentrandroid.demohhll.model

data class Data(
    var _shards: Shards,
    var hits: Hits,
    var timed_out: Boolean,
    var took: Int
)