package nguyentrandroid.a.mylibrary.modelClass

data class Data(
    var _shards: Shards,
    var hits: Hits,
    var timed_out: Boolean,
    var took: Int
)