package nguyentrandroid.a.hhll.classes.bases

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import nguyentrandroid.a.hhll.classes.interfaces.BaseView

abstract class BaseActivity:AppCompatActivity(),BaseView {
    override fun onStart() {
        super.onStart()
    }

    override fun finish() {
        super.finish()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initViews()
        observeData()
    }

    override fun observeData() {

    }
    abstract fun getContainer(): Int

    open fun goToFragment(fragment: BaseFragment, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            add(getContainer(), fragment, tag)
            addToBackStack(tag)
        }.commit()
        fragment.onShowFromActivity()
    }

    open fun hideCurFragment() {
        val fm: Fragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]
        supportFragmentManager.beginTransaction().apply {
            hide(fm)
        }.commit()
    }
    open fun showFragment(fm: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            show(fm)
        }.commit()
        (fm as BaseFragment).onShowFromActivity()
    }
    open fun hideFragment(fm: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            hide(fm)
        }.commit()
        (fm as BaseFragment).onHideFromActivity()
    }
    open fun goToFragment(fragment: BaseFragment, tag: String, argument: Bundle) {
        fragment.arguments = argument
        supportFragmentManager.beginTransaction().apply {
            add(getContainer(), fragment)
            addToBackStack(tag)
        }.commit()
    }

    open fun backFragment(): Boolean {
        if (supportFragmentManager.backStackEntryCount == 1)
            return false
        else {
            supportFragmentManager.popBackStack()
            val fm: Fragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size - 2]
            showFragment(fm)
            (fm as BaseFragment).onShowAgain()
            return true
        }
    }
}