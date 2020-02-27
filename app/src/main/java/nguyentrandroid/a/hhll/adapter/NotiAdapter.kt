package nguyentrandroid.a.hhll.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.viewholder.ViewHolderItem
import nguyentrandroid.a.hhll.viewholder.ViewHolderItemEmpty
import nguyentrandroid.a.mylibrary.modelClass.Hit
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


class ApdapterNoti(val notis: List<Hit>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val EMPTY_ITEM = 0
    private val NORMAL_ITEM = 1



    override fun getItemViewType(position: Int): Int {
        return if (notis[position]._source.iv104.equals("halo_noti_like_post") || notis[position]._source.iv104.equals("halo_noti_like_comment"))
            NORMAL_ITEM
        else
            EMPTY_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View

        return if (viewType == NORMAL_ITEM) {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_noti, parent, false)
            ViewHolderItem(binding)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
            ViewHolderItemEmpty(view)
        }
    }

    override fun getItemCount(): Int {
        return notis.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == NORMAL_ITEM){
            val viewHolder = holder as ViewHolderItem
            viewHolder.bind(notis[position])



//            if(notis[position]._source.iv104.equals("halo_noti_like_post")){
//                viewHolder.tvTitle.text = notis[position]._source.fi101[0].iv102 + " đã bày tỏ cảm xúc về bài viết của bạn"
//                viewHolder.tvIcon.text = notis[position]._source.iv107
//                Picasso.get()
//                    .load(notis[position]._source.fi101[0].iv103)
//                    .into(viewHolder.imgAvt)
//
//            }else{
//                viewHolder.tvTitle.text = notis[position]._source.fi101[0].iv102 + " đã bình luận trong bài viết của bạn"
//
//            }

        }else{
            val viewHolder = holder as ViewHolderItemEmpty
        }
    }
}
