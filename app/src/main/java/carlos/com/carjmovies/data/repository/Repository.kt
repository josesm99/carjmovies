package carlos.com.carjmovies.data.repository

import android.content.Context
import carlos.com.carjmovies.data.network.MoviesAppService

class Repository(
    private val service: MoviesAppService,
    private val context: Context
) {
    suspend fun getMovies(page: Int) = service.getITunesMovies(page)
}