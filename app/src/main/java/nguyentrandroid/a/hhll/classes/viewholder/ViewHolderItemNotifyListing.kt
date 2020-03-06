package nguyentrandroid.a.hhll.classes.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import nguyentrandroid.a.hhll.BR
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.classes.bases.BaseViewHolder
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit

class NotifyListingItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {



    fun bindTo(data: Hit?) {
        binding.setVariable(BR.noti_listing, data)
        binding.executePendingBindings()
        binding.let {

        }
    }

    companion object {
        fun create(binding: ViewDataBinding): NotifyListingItemViewHolder {
            return NotifyListingItemViewHolder(binding)
        }
    }
}