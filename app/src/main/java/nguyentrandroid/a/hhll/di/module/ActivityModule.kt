package nguyentrandroid.a.hhll.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nguyentrandroid.a.hhll.ui.MainActivity
import nguyentrandroid.a.hhll.ui.notify.NotificationFragment


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity


}