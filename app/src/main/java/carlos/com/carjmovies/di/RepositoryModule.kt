package carlos.com.carjmovies.di

import android.content.Context
import carlos.com.carjmovies.data.network.MoviesAppService
import carlos.com.carjmovies.data.repository.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { createRepository(get(), androidContext()) }
}

fun createRepository(
    movieAppService: MoviesAppService,
    context: Context
): Repository = Repository(movieAppService, context)