package nguyentrandroid.a.hhll.ui.notify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.classes.bases.BaseViewModelFactory
import nguyentrandroid.a.hhll.classes.utils.getViewModel
import nguyentrandroid.a.hhll.databinding.ActivityMainBinding
import nguyentrandroid.a.mylibrary.NotiRepository


class MainActivity : AppCompatActivity() {
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = getViewModel(MainViewModel::class.java,
            BaseViewModelFactory { MainViewModel("User",10) })

        viewModel?.listNotify?.observeForever{
            //to do
        }



    }



}

