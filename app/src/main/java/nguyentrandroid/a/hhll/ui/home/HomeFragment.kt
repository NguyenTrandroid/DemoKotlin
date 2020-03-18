package nguyentrandroid.a.hhll.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nguyentrandroid.a.hhll.R
import nguyentrandroid.a.hhll.classes.bases.BaseFragment


class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun initViews() {

    }

    override fun observeData() {
    }

}