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
import nguyentrandroid.a.hhll.classes.viewholder.ViewHolderItemNotifyListing
import nguyentrandroid.a.hhll.classes.viewholder.ViewHolderItemNotifyOff
import nguyentrandroid.a.hhll.classes.viewholder.ViewHolderItemNotifyOnl
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit

class AdapterNotifyListing(): PagedListAdapter<Hit, ViewHolderItemNotifyListing >(NotiDiff){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemNotifyListing= ViewHolderItemNotifyListing(parent)

    override fun onBindViewHolder(holder: ViewHolderItemNotifyListing, position: Int) {
        holder.bindTo(getItem(position))
    }
}
object NotiDiff : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem == newItem
    }

}
