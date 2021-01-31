package carlos.com.carjmovies.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import carlos.com.carjmovies.BuildConfig
import carlos.com.carjmovies.R
import carlos.com.carjmovies.data.model.MovieData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    private val listener: (MovieData) -> Unit
) : PagingDataAdapter<MovieData, RecyclerView.ViewHolder>(
    MovieModelComparator
) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val movieData: MovieData = getItem(position)!!

        val viewHolder = holder as MovieViewHolder
        viewHolder.itemView.movie_title.text = movieData.title
        viewHolder.itemView.movie_votes.text = viewHolder.itemView.context.getString(
            R.string.votes_text,
            movieData.vote_average.toString()
        )
        viewHolder.itemView.movie_description.text = movieData.overview

        Glide.with(viewHolder.itemView.context)
            .load(BuildConfig.BASE_IMAGES + movieData.poster_path)
            .into(viewHolder.itemView.movie_img)

        Glide.with(viewHolder.itemView.context)
            .load(BuildConfig.BASE_IMAGES + movieData.backdrop_path)
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        viewHolder.itemView.view_movie_item.setOnClickListener {
            listener.invoke(movieData)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        )
    }

    companion object {
        private val MovieModelComparator = object : DiffUtil.ItemCallback<MovieData>() {
            override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData) =
                oldItem == newItem
        }
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)
}