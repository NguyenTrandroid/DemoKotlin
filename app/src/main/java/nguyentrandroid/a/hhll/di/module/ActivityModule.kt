package nguyentrandroid.a.hhll.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nguyentrandroid.a.hhll.ui.notify.MainActivity


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity


}