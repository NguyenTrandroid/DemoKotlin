package nguyentrandroid.a.hhll.classes.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import nguyentrandroid.a.hhll.BR

class ViewHolderItemNotifyOnl(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Any) {
        binding.setVariable(BR.noti_onl,data)
        binding.executePendingBindings()
    }
}