package carlos.com.carjmovies.data.network

import carlos.com.carjmovies.data.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("3/discover/movie?api_key=d1c337e865f193fe0c206a25af6aa262")
    suspend fun getITunesMovies(
        @Query("page") page: Int
    ): Response<MovieListResponse>
}