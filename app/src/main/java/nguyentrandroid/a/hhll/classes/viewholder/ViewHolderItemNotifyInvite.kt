package nguyentrandroid.a.hhll.classes.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_invite.view.*
import nguyentrandroid.a.hhll.BR
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.classes.bases.BaseViewHolder
import nguyentrandroid.a.hhll.data.models.reponse.notify.Hit

class ViewHolderItemNotifyInvite(
    val binding: ViewDataBinding,
    private val adapterOnclick: onclickCallBack
) : RecyclerView.ViewHolder(binding.root) {


    fun bindTo(data: Hit?, position: Int) {
        binding.setVariable(BR.noti_invite, data)
        binding.executePendingBindings()
        data?._source?.checkAccept?.let {
            if (it) {
                binding.root.bt_accept.visibility = View.INVISIBLE
                binding.root.bt_cancel.visibility = View.INVISIBLE
                binding.root.tv_sttAccept.visibility=View.VISIBLE
            } else {
                binding.root.bt_accept.visibility = View.VISIBLE
                binding.root.bt_cancel.visibility = View.VISIBLE
                binding.root.tv_sttAccept.visibility=View.INVISIBLE

            }
        }
        binding.root.findViewById<Button>(R.id.bt_accept).setOnClickListener {

            adapterOnclick.onClick(it, position)

        }
        binding.root.findViewById<Button>(R.id.bt_cancel).setOnClickListener {
            adapterOnclick.onClick(it, position)

        }
    }

    companion object {
        fun create(
            binding: ViewDataBinding,
            adapterOnclick: onclickCallBack
        ): ViewHolderItemNotifyInvite {
            return ViewHolderItemNotifyInvite(binding, adapterOnclick)
        }
    }
}