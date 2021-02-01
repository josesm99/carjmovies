package carlos.com.carjmovies.data.repository

import carlos.com.carjmovies.data.network.MoviesAppService

class Repository(
    private val service: MoviesAppService
) {
    suspend fun getMovies(page: Int) = service.getITunesMovies(page)
}