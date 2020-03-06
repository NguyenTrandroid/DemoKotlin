package nguyentrandroid.a.hhll.ui.notify

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.adapter.NotifyListingAdapter
import nguyentrandroid.a.hhll.adapter.NotifyOffApdapter
import nguyentrandroid.a.hhll.adapter.NotifyOnlApdapter
import nguyentrandroid.a.hhll.classes.bases.BaseViewModelFactory
import nguyentrandroid.a.hhll.classes.utils.getViewModel
import nguyentrandroid.a.hhll.data.models.entities.ItemNotify
import nguyentrandroid.a.hhll.data.models.entities.ItemNotifyDB
import nguyentrandroid.a.hhll.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null
    var adapter: NotifyListingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = getViewModel(MainViewModel::class.java,
            BaseViewModelFactory { MainViewModel(application) })

        adapter = NotifyListingAdapter {
            getDBOff()
        }
        rv_noti.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_noti.adapter = adapter
        viewModel?.getListingNotifyOnl("5bd2ec89a7262a092eb062f7")?.pagedList?.observeForever {
            adapter?.submitList(it)

        }
        viewModel?.getListingNotifyOnl("5bd2ec89a7262a092eb062f7")?.networkState?.observeForever {

            adapter?.setNetworkState(it)
        }
    }

    private fun getDBOff() {
        val adapter = NotifyListingAdapter {
            getDBOff()
        }
        rv_noti.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_noti.adapter = adapter

        viewModel?.getListingNotifyOnl("5bd2ec89a7262a092eb062f7")?.networkState?.observeForever {
            adapter.setNetworkState(it)
        }

        viewModel?.getListingNotifyDB("5bd2ec89a7262a092eb062f7")?.pagedList?.observeForever {
            adapter?.submitList(it)
        }
    }
}