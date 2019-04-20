package harmoush.com.movies.views.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import harmoush.com.movies.R
import harmoush.com.movies.data.models.Movie
import harmoush.com.movies.utils.Constants
import harmoush.com.movies.views.activities.MainActivity

class MoviesAdapter(val context: Context?, val moviesList: ArrayList<Movie>?) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cell_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie: Movie? = moviesList?.get(position)

        if (movie != null && context != null) {

            holder.movieTitleTextView.text = movie.title
            holder.movieRateTextView.text = movie.voteAverage.toString()

            Glide.with(context)
                .load(Constants.IMAGE_BASE_URL.format(movie.posterPath))
                .fitCenter()
                .into(holder.moviePosterImageView)

            holder.favoriteButton.isSelected = movie.favorite

            holder.favoriteButton.setOnClickListener {
                (context as MainActivity).addFavoriteMovie(!movie.favorite, movie.id)
            }
        }
    }


    override fun getItemCount() = moviesList?.size ?: 0

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val moviePosterImageView: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        val movieTitleTextView: TextView = itemView.findViewById(R.id.tv_movie_title)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.btn_favorite)
        val movieRateTextView: TextView = itemView.findViewById(R.id.tv_movie_rate)
        val layoutMovieTitle: View = itemView.findViewById(R.id.layout_movie_title)

        init {
            itemView.setOnClickListener { v ->
                val args: Bundle? = Bundle()
                args?.putSerializable(Constants.MOVIE_DETAILS_KEY, moviesList?.get(adapterPosition))

                v.findNavController().navigate(R.id.action_moviesListFragment_to_movieDetailsFragment, args)
            }
        }
    }
}