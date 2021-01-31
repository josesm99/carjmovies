package carlos.com.carjmovies.ui.pager

import androidx.paging.PagingSource
import carlos.com.carjmovies.data.model.MovieData
import carlos.com.carjmovies.data.model.Result
import carlos.com.carjmovies.data.repository.Repository

class MoviePagingSource(
    private val repository: Repository
) : PagingSource<Int, MovieData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        return try {
            val nextPage = params.key ?: 1
            when (val result = repository.getMovies(nextPage)) {
                is Result.Success -> {
                    LoadResult.Page(
                        data = result.data.results,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = result.data.page?.plus(1)
                    )
                }
                is Result.Error -> {
                    throw result.error
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}