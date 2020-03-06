package nguyentrandroid.a.hhll.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nguyentrandroid.a.hhll.BR
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.classes.utils.NetworkState
import nguyentrandroid.a.hhll.classes.viewholder.NetworkStateItemViewHolder
import nguyentrandroid.a.hhll.classes.viewholder.NotifyListingItemViewHolder
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit

//class NotifyListingAdapter(private val retryCallback: () -> Unit) : PagedListAdapter<Hit, RecyclerView.ViewHolder>(NotiDiff) {
//    private var networkState: NetworkState? = null
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding: ViewDataBinding =
//            DataBindingUtil.inflate(layoutInflater, R.layout.item_notify_listing, parent, false)
//        return when (viewType) {
//            R.layout.item_notify_listing -> NotifyListingItemViewHolder.create(binding)
//            R.layout.network_state_item -> NetworkStateItemViewHolder.create(parent, retryCallback)
//            else -> throw IllegalArgumentException("unknown view type $viewType")
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (getItemViewType(position)) {
//            R.layout.item_notify_listing -> (holder as NotifyListingItemViewHolder).bindTo(getItem(position))
//            R.layout.network_state_item -> (holder as NetworkStateItemViewHolder).bindTo(networkState)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return super.getItemCount() + if (hasExtraRow()) 1 else 0
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return if (hasExtraRow() && position == itemCount - 1) {
//            R.layout.network_state_item
//        } else {
//            R.layout.item_notify_listing
//        }
//    }
//
//    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED
//
//    fun setNetworkState(newNetworkState: NetworkState?) {
//        val previousState = this.networkState
//        val hadExtraRow = hasExtraRow()
//        this.networkState = newNetworkState
//        val hasExtraRow = hasExtraRow()
//        if (hadExtraRow != hasExtraRow) {
//            if (hadExtraRow) {
//                notifyItemRemoved(super.getItemCount())
//            } else {
//                notifyItemInserted(super.getItemCount())
//            }
//        } else if (hasExtraRow && previousState != newNetworkState) {
//            notifyItemChanged(itemCount - 1)
//        }
//    }
//}
//
//object NotiDiff : DiffUtil.ItemCallback<Hit>() {
//    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
//        return oldItem._id == newItem._id
//    }
//
//    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
//        return oldItem == newItem
//    }
//
//}
