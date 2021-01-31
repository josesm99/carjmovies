package carlos.com.carjmovies.data.network

import carlos.com.carjmovies.data.model.MovieListResponse
import carlos.com.carjmovies.data.model.Result

class MoviesAppService(private val api: Api) : BaseService() {

    suspend fun getITunesMovies(page: Int): Result<MovieListResponse> {
        return createCall {
            val response = api.getITunesMovies(page)
            response
        }
    }
}