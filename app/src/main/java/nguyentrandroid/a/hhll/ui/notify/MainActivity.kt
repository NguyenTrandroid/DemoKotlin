package nguyentrandroid.a.hhll.ui.notify

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.adapter.NotifyListingAdapter
import nguyentrandroid.a.hhll.classes.bases.BaseViewModelFactory
import nguyentrandroid.a.hhll.classes.utils.Constants
import nguyentrandroid.a.hhll.classes.utils.Constants.Companion.KEY_SAVESTATE
import nguyentrandroid.a.hhll.classes.utils.getViewModel
import nguyentrandroid.a.hhll.classes.viewholder.onclickCallBack
import nguyentrandroid.a.hhll.databinding.ActivityMainBinding
import nguyentrandroid.a.hhll.di.factory.InjectingSavedStateViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity(), onclickCallBack {

    @Inject
    lateinit var abstractFactory: InjectingSavedStateViewModelFactory
    private lateinit var viewModel: MainViewModel
    var adapter: NotifyListingAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        val defArgs = bundleOf(KEY_SAVESTATE to "5bd2ec89a7262a092eb062f7")
        viewModel = ViewModelProvider(
            this,
            abstractFactory.create(this, defArgs)
        )[MainViewModel::class.java]
        adapter = NotifyListingAdapter(this@MainActivity)
        rv_noti.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_noti.adapter = adapter
        viewModel.getListingNotifyOnl().pagedList.observeForever {
            adapter?.submitList(it)
        }
        viewModel?.getDb()?.observeForever {
            Log.d("AAA","sizeDB ${it.size}")
        }

    }

//    private fun getDBOff() {
//        Log.d("AAA", "vao DB")
//        val adapterDB = NotifyListingAdapter(this)
//        rv_noti.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        rv_noti.adapter = adapterDB
//
//
//        viewModel?.networkStateDB?.observeForever {
//            adapterDB.setNetworkState(it)
//        }
//
//        viewModel?.hitsDB?.observeForever {
//            adapterDB?.submitList(it)
//        }
//    }

    override fun onClick(view: View, pos: Int) {
        when (view.id) {
            R.id.retry_button -> {
            }
            R.id.bt_accept -> {
                Log.d("AAA", "checkstt ${adapter?.currentList?.get(0)?._source?.checkAccept}")
                adapter?.currentList?.get(pos)?._source?.checkAccept = true
                adapter?.notifyItemChanged(pos)


            }
            R.id.bt_cancel -> {
                adapter?.currentList?.get(pos)?._source?.checkAccept = true
                adapter?.notifyItemChanged(pos)
            }

        }
    }

}