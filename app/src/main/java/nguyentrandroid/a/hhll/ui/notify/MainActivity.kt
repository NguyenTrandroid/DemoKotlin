package nguyentrandroid.a.hhll.ui.notify

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.classes.bases.BaseViewModelFactory
import nguyentrandroid.a.hhll.classes.bases.GlobalState
import nguyentrandroid.a.hhll.classes.utils.getViewModel
import nguyentrandroid.a.hhll.data.models.entities.ItemNotifyDB
import nguyentrandroid.a.hhll.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = getViewModel(MainViewModel::class.java,
            BaseViewModelFactory { MainViewModel("5bd2ec89a7262a092eb062f7", 10, application) })
        viewModel?.globalState?.observeForever {
            when (it.name) {
                "SHOW_LOADING" -> prb_load.visibility = View.VISIBLE
                "HIDE_LOADING" -> {
                    viewModel?.listNotify?.observeForever {
                        // use database onl
                        viewModel?.deleteDataDB()
                        it?.forEach {
                            val itemNotifyDB =
                                ItemNotifyDB(it._id, it._index, it._type, it.sort, it._source)
                            viewModel?.insert(itemNotifyDB)
                        }
                    }
                    prb_load.visibility = View.INVISIBLE
                }
                "ERROR" -> {
                    viewModel?.getAllNotify()?.observeForever {
                        // use database off
                    }
                    prb_load.visibility = View.INVISIBLE
                }
            }
        }
    }
}

