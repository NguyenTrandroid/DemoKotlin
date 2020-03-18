package nguyentrandroid.a.hhll.di.module

import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nguyentrandroid.a.hhll.di.factory.AssistedSavedStateViewModelFactory
import nguyentrandroid.a.hhll.di.factory.ViewModelKey
import nguyentrandroid.a.hhll.ui.MainViewModel


@AssistedModule
@Module(includes = [AssistedInject_ViewModelModule::class])
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindVMFactory(f: MainViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}