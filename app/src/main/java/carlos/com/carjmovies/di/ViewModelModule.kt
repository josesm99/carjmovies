package carlos.com.carjmovies.di

import carlos.com.carjmovies.ui.vm.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
}