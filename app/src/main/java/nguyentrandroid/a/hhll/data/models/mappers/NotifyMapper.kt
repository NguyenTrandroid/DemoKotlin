package nguyentrandroid.a.hhll.data.models.mappers

import android.util.Log
import nguyentrandroid.a.hhll.classes.utils.Constants
import nguyentrandroid.a.hhll.data.models.entities.ItemNotify
import nguyentrandroid.a.hhll.data.models.reponse.notify.NotifyResponse

fun NotifyResponse.toListNotify(): List<ItemNotify> {
    var listNotify = arrayListOf<ItemNotify>()
    hits?.hits.let {
        it?.forEach { it ->
            val itemNotify = ItemNotify(it._id, it._index, it._source, it._type, it.sort)
            when(itemNotify._source.iv104){
                Constants.COMMENT_COMMENT->{ itemNotify._source.title = itemNotify._source.fi101[0].iv102+" đã phản hồi trong bình luận của bạn"}
                Constants.COMMENT_POST->{itemNotify._source.title = itemNotify._source.fi101[0].iv102+" đã bình luận trong bài viết của bạn"}
                Constants.LIKE_POST->{itemNotify._source.title = itemNotify._source.fi101[0].iv102+" đã bày tỏ cảm xúc về bài viết của bạn"}
                Constants.SHARE_POST->{itemNotify._source.title = itemNotify._source.fi101[0].iv102+" đã chia sẻ bài viết của bạn"}
                Constants.LIKE_COMMENT->{itemNotify._source.title = itemNotify._source.fi101[0].iv102+" đã bày tỏ cảm xúc về bình luận của bạn"}
            }
            listNotify.add(itemNotify)
        }
    }
    return listNotify
}

