package nguyentrandroid.a.hhll.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.adapter.ApdapterNoti
import nguyentrandroid.a.hhll.databinding.ActivityMainBinding
import nguyentrandroid.a.mylibrary.NotiRepository


class MainActivity : AppCompatActivity() {
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        presenter = MainPresenter(NotiRepository(application))
//        presenter?.data?.observe(this@MainActivity, Observer {
//            if (it != null) {
//                val adapter =
//                    ApdapterNoti(it.hits.hits)
//                rv_noti.adapter = adapter
//                prb_load.visibility = View.INVISIBLE
//            } else {
//                Log.d("NoData", "Null")
//            }
//        })

    }


}

