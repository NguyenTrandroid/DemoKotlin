package nguyentrandroid.a.hhll.ui

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.adapter.ViewPagerAdapter
import nguyentrandroid.a.hhll.classes.bases.BaseActivity
import nguyentrandroid.a.hhll.classes.utils.Constants.Companion.KEY_SAVESTATE
import nguyentrandroid.a.hhll.databinding.ActivityMainBinding
import nguyentrandroid.a.hhll.di.factory.InjectingSavedStateViewModelFactory
import nguyentrandroid.a.hhll.ui.home.HomeFragment
import nguyentrandroid.a.hhll.ui.notify.NotificationFragment
import javax.inject.Inject


class MainActivity : BaseActivity() {
    @Inject
    lateinit var abstractFactory: InjectingSavedStateViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun getContainer(): Int {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setupViewPager(binding)
    }

    override fun initViews() {
//        val defArgs = bundleOf(KEY_SAVESTATE to "5bd2ec89a7262a092eb062f7")
//        viewModel = ViewModelProvider(
//            this,
//            abstractFactory.create(this, defArgs)
//        )[MainViewModel::class.java]
    }

    override fun observeData() {

    }

    private fun setupViewPager(binding: ActivityMainBinding) {
        val adapter = ViewPagerAdapter(getSupportFragmentManager())
        var homeFragment = HomeFragment()
        var notiFragment = NotificationFragment()
        adapter.addFragment(homeFragment, "Home")
        adapter.addFragment(notiFragment, "Notification")
        vp_fragments_container.adapter = adapter
        tl_tabs_container.setupWithViewPager(vp_fragments_container)
    }
}