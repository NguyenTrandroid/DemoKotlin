package nguyentrandroid.a.hhll.classes.bases

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment
import nguyentrandroid.a.hhll.classes.interfaces.BaseView

abstract class BaseFragment : DaggerFragment(), BaseView {
    var container: FragmentCallback? = null
    val TAG: String = javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.isClickable = true
        initViews()
        observeData()
    }

    fun setArgs(bundle: Bundle?, notify: Boolean = false) {
        arguments = bundle
        if (notify)
            onNewArgs(arguments)
    }
    open fun onShowAgain(){}

    protected open fun onNewArgs(args: Bundle?) {}
    open fun backHandler(): Boolean {
        return true
    }

    open fun onBackPressed(): Boolean {
        return when {
            !backHandler() -> false
            else -> true
        }
    }

    open fun goToFragment(fragment: BaseFragment, tag: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).goToFragment(fragment, tag)
        }
    }

    open fun goToFragment(fragment: BaseFragment, tag: String, argument: Bundle) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).goToFragment(fragment, tag, argument)
        }
    }

    open fun backFragment() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).backFragment()
        }
    }

    open fun onShowFromActivity() {}

    open fun onHideFromActivity() {}

}

interface FragmentCallback {
    fun backPressed() {}
}