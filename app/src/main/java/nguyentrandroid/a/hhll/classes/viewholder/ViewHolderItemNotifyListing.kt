package nguyentrandroid.a.hhll.classes.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import nguyentrandroid.a.hhll.BR
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit

class ViewHolderItemNotifyListing(parent : ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_notify_listing, parent, false)) {
    var hit : Hit? = null
    fun bindTo(hit: Hit?) {
        this.hit = hit
    }
}