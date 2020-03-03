package nguyentrandroid.a.hhll.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.viewholder.ViewHolderItem
import nguyentrandroid.a.hhll.viewholder.ViewHolderItemEmpty
import nguyentrandroid.a.mylibrary.modelClass.Hit


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
        }else{
            val viewHolder = holder as ViewHolderItemEmpty
        }
    }
}
