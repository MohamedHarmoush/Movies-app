package harmoush.com.movies.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import harmoush.com.movies.R
import harmoush.com.movies.data.models.Movie
import harmoush.com.movies.utils.Constants
import harmoush.com.movies.views.activities.MainActivity

class MovieDetailsFragment : Fragment() {


    private lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movie = arguments?.getSerializable(Constants.MOVIE_DETAILS_KEY) as Movie

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragment = inflater.inflate(R.layout.fragment_movie_details, container, false)
        initUi(fragment)

        return fragment
    }

    private fun initUi(fragment: View) {

        val movieTitleTextView: TextView = fragment.findViewById(R.id.tv_movie_title)
        val movieOverviewTextView: TextView = fragment.findViewById(R.id.tv_movie_summary)
        val movieReleaseDateTextView: TextView = fragment.findViewById(R.id.tv_release_date)
        val movieRateTextView: TextView = fragment.findViewById(R.id.tv_movie_rate)
        val moviePosterTextView: ImageView = fragment.findViewById(R.id.iv_movie_poster)
        val favoriteButton: ImageButton = fragment.findViewById(R.id.btn_favorite)

        val trailersRecyclerView: RecyclerView = fragment.findViewById(R.id.rv_trailers)
        val reviewsRecyclerView: RecyclerView = fragment.findViewById(R.id.rv_reviews)

        movieTitleTextView.text = movie.title
        movieReleaseDateTextView.text = getString(R.string.release_date_s).format(movie.releaseDate)
        movieRateTextView.text = movie.voteAverage.toString()
        movieOverviewTextView.text = movie.overview
        favoriteButton.isSelected = movie.favorite

        favoriteButton.setOnClickListener {
            (context as MainActivity).addFavoriteMovie(!movie.favorite, movie.id)
            movie.favorite = !movie.favorite
            favoriteButton.isSelected = movie.favorite
        }

        Glide.with(this)
                .load(Constants.IMAGE_BASE_URL.format(movie.posterPath))
                .into(moviePosterTextView)

        initToolbar(fragment)
    }

    private fun initToolbar(fragment: View) {
        val toolbar: Toolbar = fragment.findViewById(R.id.toolbar)
        val appBarLayout: AppBarLayout = fragment.findViewById(R.id.appbar_layout)
        val collapsingToolbarLayout: CollapsingToolbarLayout = fragment.findViewById(R.id.collapsing_toolbar)
        collapsingToolbarLayout.title = ""

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            if (appBarLayout.totalScrollRange + verticalOffset == 0) {
                collapsingToolbarLayout.title = getString(R.string.movie_details)
            } else {
                collapsingToolbarLayout.title = ""
            }

        })
        (context as MainActivity).setSupportActionBar(toolbar)
        (context as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationIcon(R.drawable.back_icon)
        toolbar.setNavigationOnClickListener { v -> (context as MainActivity).onBackPressed() }
    }

}