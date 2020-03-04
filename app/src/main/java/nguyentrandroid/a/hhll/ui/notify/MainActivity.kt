package nguyentrandroid.a.hhll.ui.notify

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.adapter.AdapterNotifyListing
import nguyentrandroid.a.hhll.adapter.NotifyOffApdapter
import nguyentrandroid.a.hhll.adapter.NotifyOnlApdapter
import nguyentrandroid.a.hhll.classes.bases.BaseViewModelFactory
import nguyentrandroid.a.hhll.classes.utils.getViewModel
import nguyentrandroid.a.hhll.data.models.entities.ItemNotify
import nguyentrandroid.a.hhll.data.models.entities.ItemNotifyDB
import nguyentrandroid.a.hhll.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = getViewModel(MainViewModel::class.java,
            BaseViewModelFactory { MainViewModel("5bd2ec89a7262a092eb062f7", 50, application) })
//        viewModel?.globalState?.observeForever {
//            when (it.name) {
//                "SHOW_LOADING" -> prb_load.visibility = View.VISIBLE
//                "HIDE_LOADING" -> {
//                    viewModel?.listNotify?.observeForever {
//                        // use database onl
//                        saveDataOff(it)
//                        val adapter = NotifyOnlApdapter(it)
//                        rv_noti.adapter = adapter
//                    }
//                    prb_load.visibility = View.INVISIBLE
//                }
//                "ERROR" -> {
//                    viewModel?.getAllNotify()?.observeForever {
//                        // use database off
//
//                        val adapter = NotifyOffApdapter(it)
//                        rv_noti.adapter = adapter
//                    }
//                    prb_load.visibility = View.INVISIBLE
//                }
//            }
//        }
        viewModel?.getListing("5bd2ec89a7262a092eb062f7")?.pagedList?.observeForever {
            val adapter = AdapterNotifyListing()
            adapter.submitList(it)
        }
    }

//    private fun saveDataOff(listItemNotify: List<ItemNotify>) {
//        viewModel?.deleteDataDB()
//        listItemNotify.let {
//            var i: Int = 0
//            while (i < listItemNotify.size && i < 10) {
//                val itemNotifyDB =
//                    ItemNotifyDB(it[i]._id, it[i]._index, it[i]._type, it[i].sort, it[i]._source)
//                viewModel?.insert(itemNotifyDB)
//                i++
//            }
//        }
//    }
}

