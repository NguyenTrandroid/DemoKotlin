package nguyentrandroid.a.hhll.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.classes.utils.Constants
import nguyentrandroid.a.hhll.data.models.entities.ItemNotify
import nguyentrandroid.a.hhll.classes.viewholder.ViewHolderItemNotifyOnl
import nguyentrandroid.a.hhll.classes.viewholder.ViewHolderItemEmpty


class NotifyOnlApdapter(val notifysOn: List<ItemNotify>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val EMPTY_ITEM = 0
    private val NORMAL_ITEM = 1
    override fun getItemViewType(position: Int): Int {
        return if (notifysOn[position]._source.iv104.equals(Constants.SHARE_POST) ||
            notifysOn[position]._source.iv104.equals(Constants.COMMENT_POST) ||
            notifysOn[position]._source.iv104.equals(Constants.LIKE_POST) ||
            notifysOn[position]._source.iv104.equals(Constants.COMMENT_COMMENT) ||
            notifysOn[position]._source.iv104.equals(Constants.LIKE_COMMENT)
        )
            NORMAL_ITEM
        else
            EMPTY_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View

        return if (viewType == NORMAL_ITEM) {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_notify_onl, parent, false)
            ViewHolderItemNotifyOnl(binding)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
            ViewHolderItemEmpty(view)
        }
    }

    override fun getItemCount(): Int {
        return notifysOn.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == NORMAL_ITEM) {
            val viewHolder = holder as ViewHolderItemNotifyOnl
            viewHolder.bind(notifysOn[position])
        } else {
            val viewHolder = holder as ViewHolderItemEmpty
        }
    }
}
