package nguyentrandroid.a.hhll.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nguyentrandroid.a.hhll.ui.home.HomeFragment
import nguyentrandroid.a.hhll.ui.notify.NotificationFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
     abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
     abstract fun notiFragment(): NotificationFragment

}