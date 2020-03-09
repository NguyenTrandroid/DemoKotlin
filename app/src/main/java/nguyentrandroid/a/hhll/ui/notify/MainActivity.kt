package nguyentrandroid.a.hhll.ui.notify

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.adapter.NotifyListingAdapter
import nguyentrandroid.a.hhll.classes.bases.BaseViewModelFactory
import nguyentrandroid.a.hhll.classes.utils.Constants
import nguyentrandroid.a.hhll.classes.utils.getViewModel
import nguyentrandroid.a.hhll.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null
    var adapter: NotifyListingAdapter? = null
    var handle = SavedStateHandle()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        handle.set(Constants.KEY_SAVESTATE, "5bd2ec89a7262a092eb062f7")
        viewModel = getViewModel(MainViewModel::class.java,
            BaseViewModelFactory { MainViewModel(application, handle) })


        adapter = NotifyListingAdapter {
            viewModel?.retryOnl("5bd2ec89a7262a092eb062f7")

        }
        rv_noti.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_noti.adapter = adapter
        viewModel?.getListingNotifyOnl("5bd2ec89a7262a092eb062f7")?.pagedList?.observeForever {
            adapter?.submitList(it)
        }
        viewModel?.getListingNotifyOnl("5bd2ec89a7262a092eb062f7")?.networkState?.observeForever {
            adapter?.setNetworkState(it)

            if (it.status.toString().equals("FAILED")) {
                getDBOff()
            } else {
                adapter?.setNetworkState(it)

            }
        }

        viewModel?.getSizeDB()?.observeForever {
            Log.d("AAA", "Size ${it.size}")
        }


    }

    private fun getDBOff() {
        Log.d("AAA", "vao DB")
        val adapterDB = NotifyListingAdapter {
            viewModel?.retryDB()
        }
        rv_noti.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_noti.adapter = adapterDB

        viewModel?.networkStateDB?.observeForever {
            adapterDB.setNetworkState(it)
        }

        viewModel?.hitsDB?.observeForever {
            adapterDB?.submitList(it)
        }
    }
}