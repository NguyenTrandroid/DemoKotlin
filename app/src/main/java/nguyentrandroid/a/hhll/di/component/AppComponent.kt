package nguyentrandroid.a.hhll.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import nguyentrandroid.a.hhll.AppController
import nguyentrandroid.a.hhll.di.module.*
import javax.inject.Singleton

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(appController: AppController)
}