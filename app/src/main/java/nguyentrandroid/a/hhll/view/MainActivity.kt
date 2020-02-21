package nguyentrandroid.a.hhll.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.model.repos.HomeRepository
import nguyentrandroid.demohhll.model.Data

class MainActivity : AppCompatActivity() {
    private var presenter: MainPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(HomeRepository())
        presenter!!._data.observe(this@MainActivity, Observer {
            // lm gì đó...
        })
    }
}

