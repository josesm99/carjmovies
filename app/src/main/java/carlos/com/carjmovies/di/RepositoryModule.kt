package carlos.com.carjmovies.di

import carlos.com.carjmovies.data.network.MoviesAppService
import carlos.com.carjmovies.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { createRepository(get()) }
}

fun createRepository(
    movieAppService: MoviesAppService
): Repository = Repository(movieAppService)