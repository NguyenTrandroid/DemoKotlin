package nguyentrandroid.a.hhll.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_notify_listing.view.*
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit

class NotifyListingAdapter() : PagedListAdapter<Hit, ViewHolder>(NotiDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notify_listing, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.title.text = item?._id

    }


}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


object NotiDiff : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem == newItem
    }

}
