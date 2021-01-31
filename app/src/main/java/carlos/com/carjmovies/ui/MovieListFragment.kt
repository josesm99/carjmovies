package carlos.com.carjmovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import carlos.com.carjmovies.R
import carlos.com.carjmovies.data.model.MovieData
import carlos.com.carjmovies.ui.adapters.MovieAdapter
import carlos.com.carjmovies.ui.adapters.MovieLoadStateAdapter
import carlos.com.carjmovies.ui.vm.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModel()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        initViews(view)
        loadMovies()
    }

    private fun loadMovies() {
        lifecycleScope.launch {
            movieAdapter.refresh()
            viewModel.createPagingDataFlow().collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }

    private fun initViews(view: View) {
        initToolbar(view)
        initRecycler()
    }

    private fun initRecycler() {
        movieAdapter = MovieAdapter(::onClickMovieItem)

        movie_recycler.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            setHasFixedSize(true)

            adapter = movieAdapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter { movieAdapter.retry() }
            )

            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), linearLayoutManager.orientation)
            movie_recycler.addItemDecoration(dividerItemDecoration)
        }



        movieAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                errorState?.let {
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun onClickMovieItem(movieData: MovieData) {
        findNavController().navigate(
            R.id.action_movieListFragment_to_movieDetailFragment,
            bundleOf(SONG_DATA to movieData)
        )
    }

    private fun initToolbar(view: View) {
        /*
       val topAppBar = view.findViewById<Toolbar>(R.id.topAppBar)


       val searchView = topAppBar.menu.findItem(R.id.search).actionView as SearchView
       searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

           override fun onQueryTextChange(newText: String): Boolean {
               Log.i("onQueryTextChange", newText)
               return true
           }

           override fun onQueryTextSubmit(query: String): Boolean {
               currentTerm = query
               viewModel.saveSearchedMovies(query)
               progressBar.visibility = View.VISIBLE

               hideKeyboard()
               return true
           }

       })
       */
    }

    private fun initObservables() {

        /*
        viewModel.saveSearch.observe(viewLifecycleOwner, Observer {
            it?.let {
                lifecycleScope.launch {
                    movieAdapter.refresh()
                    viewModel.createPagingDataFlow(currentTerm!!).collectLatest {
                        movieAdapter.submitData(it)
                    }
                }
            }
        })
        */

    }
}