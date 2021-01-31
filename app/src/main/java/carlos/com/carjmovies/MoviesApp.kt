package carlos.com.carjmovies

import android.app.Application
import carlos.com.carjmovies.di.networkModule
import carlos.com.carjmovies.di.repositoryModule
import carlos.com.carjmovies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MoviesApp)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}