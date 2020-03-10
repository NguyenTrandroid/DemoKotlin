package nguyentrandroid.a.hhll.classes.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import nguyentrandroid.a.hhll.R

class ViewHolderItemEmpty(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup): ViewHolderItemEmpty {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
            return ViewHolderItemEmpty(view)
        }
    }


}