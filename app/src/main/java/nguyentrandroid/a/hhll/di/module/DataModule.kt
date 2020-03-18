package nguyentrandroid.a.hhll.di.module

import dagger.Binds
import dagger.Module
import nguyentrandroid.a.hhll.classes.interfaces.NotificationRepositoryInterface
import nguyentrandroid.a.hhll.data.repositories.NotifyRepositories
import javax.inject.Singleton

@Module(includes = [ApiModule::class, DBModule::class])
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun notificationRepository(notifyRepositories: NotifyRepositories): NotificationRepositoryInterface
}