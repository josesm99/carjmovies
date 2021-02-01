package carlos.com.carjmovies.data.repository

import carlos.com.carjmovies.data.model.MovieListResponse
import carlos.com.carjmovies.data.network.Api
import carlos.com.carjmovies.data.network.MoviesAppService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import retrofit2.Response

@RunWith(JUnit4::class)
class RepositoryTest {

    private lateinit var moviesApi: Api
    private lateinit var moviesAppService: MoviesAppService
    private lateinit var repository: Repository
    private val validPage = 2
    private val invalidPage = -1
    private val movieListResponse = MovieListResponse(2, Mockito.anyList(), 100, 2000)
    private val moviewResponse = Response.success(movieListResponse)

    @Before
    fun setUp() {
        moviesApi = mock()
        moviesAppService = MoviesAppService(moviesApi)

        runBlocking {
            whenever(moviesApi.getITunesMovies(2)).thenReturn(moviewResponse)
        }

        repository = Repository(moviesAppService)
    }

    @Test
    fun validRequest() =
            runBlocking {
                assertEquals(moviesAppService, repository.getMovies(validPage))
            }
}