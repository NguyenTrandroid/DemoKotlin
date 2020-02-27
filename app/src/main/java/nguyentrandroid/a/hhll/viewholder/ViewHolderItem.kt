package nguyentrandroid.a.hhll.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import nguyentrandroid.a.hhll.BR

class ViewHolderItem(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Any) {
        binding.setVariable(BR.noti,data)
        binding.executePendingBindings()

    }
}