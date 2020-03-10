package nguyentrandroid.a.hhll.classes.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.classes.utils.NetworkState
import nguyentrandroid.a.hhll.classes.utils.Status

class NetworkStateItemViewHolder(view: View, private val adapterOnclick: onclickCallBack) :
    RecyclerView.ViewHolder(view) {
    private val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
    private val retry = view.findViewById<Button>(R.id.retry_button)
    private val errorMsg = view.findViewById<TextView>(R.id.error_msg)
    fun bindTo(networkState: NetworkState?, pos: Int) {
        progressBar.visibility = toVisibility(networkState?.status == Status.RUNNING)
        retry.visibility = toVisibility(networkState?.status == Status.FAILED)
        errorMsg.visibility = toVisibility(networkState?.msg != null)
        errorMsg.text = networkState?.msg
        retry.setOnClickListener {
            adapterOnclick.onClick(it, pos)
        }
    }

    companion object {
        fun create(parent: ViewGroup, adapterOnclick: onclickCallBack): NetworkStateItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.network_state_item, parent, false)
            return NetworkStateItemViewHolder(view, adapterOnclick)
        }

        fun toVisibility(constraint: Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}