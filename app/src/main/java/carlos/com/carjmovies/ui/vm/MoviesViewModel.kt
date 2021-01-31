package carlos.com.carjmovies.ui.vm

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import carlos.com.carjmovies.data.repository.Repository
import carlos.com.carjmovies.ui.pager.MoviePagingSource

class MoviesViewModel(private val repository: Repository) : ViewModel() {

    fun createPagingDataFlow() =
        Pager(PagingConfig(20)) {
            MoviePagingSource(repository)
        }.flow
}
