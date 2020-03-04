package nguyentrandroid.a.hhll.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.classes.utils.Constants
import nguyentrandroid.a.hhll.classes.viewholder.ViewHolderItemEmpty
import nguyentrandroid.a.hhll.classes.viewholder.ViewHolderItemNotifyOff
import nguyentrandroid.a.hhll.classes.viewholder.ViewHolderItemNotifyOnl
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit

class AdapterNotifyListing(): PagedListAdapter<Hit, RecyclerView.ViewHolder>(NotiDiff){

    override fun getItemViewType(position: Int): Int {
        return if (isValid(position)){
            R.layout.item_notify_listing
        }else{
            R.layout.item_empty
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == R.layout.item_notify_listing) {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_notify_listing, parent, false)
            ViewHolderItemNotifyOnl(binding)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
            ViewHolderItemEmpty(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == R.layout.item_notify_listing) {
            val viewHolder = holder as ViewHolderItemNotifyOnl
            getItem(position)?.let { viewHolder.bind(it) }
        } else {
            val viewHolder = holder as ViewHolderItemEmpty
        }
    }

    private fun isValid(position: Int) = currentList?.get(position)?._source?.iv104 == Constants.SHARE_POST


}

object NotiDiff : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem == newItem
    }

}
