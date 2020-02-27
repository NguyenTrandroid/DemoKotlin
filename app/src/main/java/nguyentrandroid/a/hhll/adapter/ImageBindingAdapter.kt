package nguyentrandroid.a.hhll.adapter

import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: CircleImageView, url: String) {
        Picasso.get().load(url).into(view)
    }
}