package nguyentrandroid.a.hhll.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isInvisible
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.a.hhll.ApdapterNoti
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.mylibrary.HomeRepository


class MainActivity : AppCompatActivity() {
    private var presenter: MainPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(HomeRepository())
        presenter?.getData()?.observe(this@MainActivity, Observer {
            val adapter = ApdapterNoti(it.hits.hits)
            rv_noti.adapter=adapter
            prb_load.visibility = View.INVISIBLE


        })

    }
}

