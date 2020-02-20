package nguyentrandroid.a.hhll

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.demohhll.model.Data
import nguyentrandroid.demohhll.model.Fi101


class MainActivity : AppCompatActivity() {
    private val apiService by lazy { RetrofitCl.create() }
    private var disposable: Disposable? = null
    private lateinit var adapter: Apdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        disposable = apiService.GetData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> UpdateUi(data = result) },
                { error -> Log.d("AAA", "" + error) }
            )
    }

    private fun UpdateUi(data: Data) {
        prb_load.visibility = View.INVISIBLE
        adapter = Apdapter(data.hits.hits)
        rv_noti.adapter = adapter
    }
}

