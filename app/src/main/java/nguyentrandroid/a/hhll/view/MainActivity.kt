package nguyentrandroid.a.hhll.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.a.hhll.adapter.ApdapterNoti
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.mylibrary.NotiRepository


class MainActivity : AppCompatActivity() {
    private var presenter: MainPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(NotiRepository())
        presenter?.getData()?.observe(this@MainActivity, Observer {
            if(it!=null){
                val adapter =
                    ApdapterNoti(it.hits.hits)
                rv_noti.adapter=adapter
                prb_load.visibility = View.INVISIBLE
            }else{
                Log.d("NoData","Null")
            }




        })

    }
}

