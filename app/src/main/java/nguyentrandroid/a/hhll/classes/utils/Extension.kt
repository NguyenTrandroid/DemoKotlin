package nguyentrandroid.a.hhll.classes.utils

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

internal fun <VM : ViewModel> FragmentActivity.getViewModel(
    vmClass: Class<VM>,
    vmFactory: ViewModelProvider.Factory? = null
): VM {
    return vmFactory?.let { ViewModelProviders.of(this, it).get(vmClass) } ?: ViewModelProviders.of(
        this
    ).get(vmClass)
}