package nguyentrandroid.a.hhll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import nguyentrandroid.demohhll.model.Fi101
import nguyentrandroid.demohhll.model.Hit
import nguyentrandroid.demohhll.model.Hits

class Apdapter(val notis: List<Hit>) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_noti, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notis.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Picasso.get()
            .load(notis[position]._source.fi101[0].iv103)
            .into(holder.imgAvatar)
        holder.tvName.text = notis[position]._source.fi101[0].iv102
    }

}

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgAvatar: CircleImageView = itemView.findViewById(R.id.img_avatar)
    val tvName: TextView = itemView.findViewById(R.id.tv_title)
}