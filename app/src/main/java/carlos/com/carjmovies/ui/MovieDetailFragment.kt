package carlos.com.carjmovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import carlos.com.carjmovies.BuildConfig
import carlos.com.carjmovies.R
import carlos.com.carjmovies.data.model.MovieData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie.*

const val SONG_DATA = "movieData"

class MovieDetailFragment : Fragment() {


    private var movieData: MovieData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            movieData = bundle.getParcelable(SONG_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        initData()
        initToolbar(view)
    }

    private fun initData() {
        movie_title.text = movieData!!.title
        movie_description.text = movieData!!.overview
        movie_votes.text =
            requireContext().getString(R.string.votes_text, movieData!!.vote_average.toString())

        Glide.with(requireContext())
            .load(BuildConfig.BASE_IMAGES + movieData!!.backdrop_path)
            .into(movie_backdrop)
    }

    private fun initToolbar(view: View) {
        main_toolbar.setNavigationIcon(R.drawable.ic_back_24);
        main_toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }
}