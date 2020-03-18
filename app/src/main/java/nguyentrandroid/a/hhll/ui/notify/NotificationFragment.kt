package nguyentrandroid.a.hhll.ui.notify

import android.content.Context
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_notification.*
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.adapter.NotifyListingAdapter
import nguyentrandroid.a.hhll.classes.bases.BaseFragment
import nguyentrandroid.a.hhll.classes.utils.Constants
import nguyentrandroid.a.hhll.classes.viewholder.onclickCallBack
import nguyentrandroid.a.hhll.di.factory.InjectingSavedStateViewModelFactory
import nguyentrandroid.a.hhll.ui.MainViewModel
import javax.inject.Inject

class NotificationFragment : BaseFragment(), onclickCallBack {

    @Inject
    lateinit var abstractFactory: InjectingSavedStateViewModelFactory
    private lateinit var viewModel: MainViewModel
    lateinit var adapter: NotifyListingAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun initViews() {
        val defArgs = bundleOf(Constants.KEY_SAVESTATE to "5bd2ec89a7262a092eb062f7")
        viewModel = ViewModelProvider(
            this,
            abstractFactory.create(this, defArgs)
        )[MainViewModel::class.java]
        adapter = NotifyListingAdapter(this)
        rv_noti.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_noti.adapter = adapter
    }

    override fun observeData() {
        viewModel?.getListingNotifyOnl()?.pagedList?.observeForever {
            adapter?.submitList(it)
        }

    }

    override fun onClick(view: View, pos: Int) {
        when (view.id) {
            R.id.retry_button -> {
            }
            R.id.bt_accept -> {
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