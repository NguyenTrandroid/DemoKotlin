package nguyentrandroid.a.hhll.adapter

import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: CircleImageView, url: String?) {
        Picasso.get().load(url?:"https://media-cdn.hahalolo.com/5d356aacaf327704db8e865f/veRXqwwlnVz8lXIN.png")?.into(view)
    }
}