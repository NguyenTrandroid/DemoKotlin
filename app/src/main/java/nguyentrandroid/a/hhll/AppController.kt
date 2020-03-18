package nguyentrandroid.a.hhll

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import nguyentrandroid.a.hhll.di.component.DaggerAppComponent
import javax.inject.Inject

class AppController : Application(), HasActivityInjector,HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjectorActivity: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidInjectorFragment: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjectorActivity
    }
    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjectorFragment
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }


}