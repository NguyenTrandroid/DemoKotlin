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

class NetworkStateItemViewHolder(view: View, private val retryCallback: () -> Unit): RecyclerView.ViewHolder(view) {
    private val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
    private val retry = view.findViewById<Button>(R.id.retry_button)
    private val errorMsg = view.findViewById<TextView>(R.id.error_msg)
    init {
        retry.setOnClickListener {
            retryCallback()
        }
    }
    fun bindTo(networkState: NetworkState?) {
        Log.d("AAA","stateNetwork"+ networkState?.status)
        progressBar.visibility = toVisibility(networkState?.status == Status.RUNNING)
        retry.visibility = toVisibility(networkState?.status == Status.FAILED)
        errorMsg.visibility = toVisibility(networkState?.msg != null)
        errorMsg.text = networkState?.msg
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.network_state_item, parent, false)
            return NetworkStateItemViewHolder(view, retryCallback)
        }

        fun toVisibility(constraint : Boolean): Int {
            return if (constraint) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}