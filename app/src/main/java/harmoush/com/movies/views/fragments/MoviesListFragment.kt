package harmoush.com.movies.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import harmoush.com.movies.R
import harmoush.com.movies.data.models.Movie
import harmoush.com.movies.utils.Constants
import harmoush.com.movies.utils.ResponseStatus
import harmoush.com.movies.viewmodels.MoviesViewModel
import harmoush.com.movies.views.adapters.MoviesAdapter


class MoviesListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private val TAG: String? = MoviesListFragment::class.simpleName

    private var moviesList: ArrayList<Movie> = ArrayList()
    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var sortByRadioGroup: RadioGroup
    private lateinit var filterByRadioGroup: RadioGroup

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        moviesViewModel.getMoviesList().observe(this, Observer { movies ->

            Log.d("com.test", "size> " + movies.size)

            if (!movies.isEmpty()) {
                showProgressBar(false)
            }
            moviesList.clear()
            moviesList.addAll(movies)
            moviesAdapter.notifyDataSetChanged()
        })

        moviesViewModel.message.observe(this, Observer {

            showProgressBar(false)
            if (!it.handled) {
                Snackbar.make(sortByRadioGroup, it.message, Snackbar.LENGTH_SHORT).show()
                moviesViewModel.message.value = ResponseStatus(it.message, true)
            }
        })

        moviesViewModel.getMoviesFromDatabase()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragment = inflater.inflate(R.layout.fragment_movies_list, container, false)

        initUi(fragment)

        return fragment
    }

    private fun initUi(fragment: View) {

        val moviesRecyclerView: RecyclerView = fragment.findViewById(R.id.rv_movies_list)
        val refreshButton: Button = fragment.findViewById(R.id.btn_refresh)
        val sortByTitleRadioButton: RadioButton = fragment.findViewById(R.id.btn_sort_by_title)
        val sortByVoteRadioButton: RadioButton = fragment.findViewById(R.id.btn_sort_by_vote)
        val filterByLikeRadioButton: RadioButton = fragment.findViewById(R.id.btn_filter_by_like)
        val filterByUnlikeRadioButton: RadioButton = fragment.findViewById(R.id.btn_filter_by_unlike)
        val filterAllButton: RadioButton = fragment.findViewById(R.id.btn_all)

        sortByRadioGroup = fragment.findViewById(R.id.rg_sort_by)
        filterByRadioGroup = fragment.findViewById(R.id.rg_filter_by)
        swipeRefreshLayout = fragment.findViewById(R.id.swipe_refresh)
        progressBar = fragment.findViewById(R.id.progress_bar)

        moviesAdapter = MoviesAdapter(activity, moviesList)
        moviesRecyclerView.adapter = moviesAdapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        refreshButton.setOnClickListener(this)
        sortByTitleRadioButton.setOnClickListener(this)
        sortByVoteRadioButton.setOnClickListener(this)
        filterByLikeRadioButton.setOnClickListener(this)
        filterByUnlikeRadioButton.setOnClickListener(this)
        filterAllButton.setOnClickListener(this)

        swipeRefreshLayout.setOnRefreshListener(this)
    }

    private fun showProgressBar(show: Boolean) {
        when {
            show -> progressBar.visibility = View.VISIBLE
            else -> progressBar.visibility = View.GONE
        }
    }

    override fun onRefresh() {

        swipeRefreshLayout.isRefreshing = false
        showProgressBar(true)

        fetchMovies(true)
    }

    private fun fetchMovies(onRefresh: Boolean) {

        if (onRefresh) {

            filterByRadioGroup.check(R.id.btn_all)
            sortByRadioGroup.check(R.id.btn_sort_by_vote)
        }

        moviesViewModel.getAllMovies(onRefresh)
    }

    private fun getFilterAttribute(): Int = when {
        filterByRadioGroup.checkedRadioButtonId == R.id.btn_all -> Constants.OPTION_NONE
        filterByRadioGroup.checkedRadioButtonId == R.id.btn_filter_by_like -> Constants.OPTION_ONE
        else -> Constants.OPTION_TWO
    }

    private fun getSortAttribute(): Int = when {
        sortByRadioGroup.checkedRadioButtonId == -1 -> Constants.OPTION_NONE
        sortByRadioGroup.checkedRadioButtonId == R.id.btn_sort_by_title -> Constants.OPTION_ONE
        else -> Constants.OPTION_TWO
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_refresh -> onRefresh()
            R.id.btn_sort_by_vote, R.id.btn_sort_by_title,
            R.id.btn_filter_by_like, R.id.btn_filter_by_unlike, R.id.btn_all -> {
                moviesViewModel.filterSortMoviesBy(getSortAttribute(), getFilterAttribute())
            }
        }
    }
}