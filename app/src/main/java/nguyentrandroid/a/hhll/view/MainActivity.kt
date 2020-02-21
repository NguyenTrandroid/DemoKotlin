package nguyentrandroid.a.hhll.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.model.repos.HomeRepository
import nguyentrandroid.demohhll.model.Data

class MainActivity : AppCompatActivity(),
    MainContract.View {
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(HomeRepository())

        presenter!!.dataResult.observe(this@MainActivity, Observer {


        })
    }

    override fun showWait() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeWait() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showData(data: Data) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

