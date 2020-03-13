package nguyentrandroid.a.hhll.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import nguyentrandroid.a.hhll.data.db.NotifyDao
import nguyentrandroid.a.hhll.data.db.NotifyDatabase
import javax.inject.Singleton

@Module
class DBModule {
    @Provides
    @Singleton
    internal fun notifyDatabase(application: Application): NotifyDatabase {
        return Room.databaseBuilder(
            application, NotifyDatabase::class.java, "notify_database"
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(appDatabase: NotifyDatabase): NotifyDao {
        return appDatabase.dao()
    }
}