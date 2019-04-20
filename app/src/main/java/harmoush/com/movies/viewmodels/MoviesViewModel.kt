package harmoush.com.movies.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import harmoush.com.movies.data.MovieRepository
import harmoush.com.movies.data.models.Movie
import harmoush.com.movies.utils.ResponseStatus

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepository: MovieRepository = MovieRepository(application)

    private var moviesList: LiveData<List<Movie>>? = null

    private var movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val message: MutableLiveData<ResponseStatus> = moviesRepository.message

    private var observer: Observer<List<Movie>> = Observer { movies ->
        this.movies.value = movies
    }

    private var fetchFromDatabase = true

    fun getMoviesFromDatabase(){
        if (fetchFromDatabase) {
            getAllMovies(false)
            fetchFromDatabase = false
        }
    }

    fun getMoviesList(): LiveData<List<Movie>> = movies

    fun getAllMovies(onRefresh: Boolean) {

        moviesList?.removeObserver(observer)
        moviesList = moviesRepository.getAllMovies(onRefresh)
        Log.d("TEST", moviesList.toString())
        moviesList?.observeForever(observer)
    }

    fun addFavoriteMovie(markFavorite: Boolean, movieId: Int) = moviesRepository.addFavoriteMovie(markFavorite, movieId)

    fun filterSortMoviesBy(SORT_ATTRIBUTE: Int, FILTER_ATTRIBUTE: Int) {

        moviesList?.removeObserver(observer)
        moviesList = moviesRepository.filterSortMoviesBy(SORT_ATTRIBUTE, FILTER_ATTRIBUTE)
        moviesList?.observeForever(observer)
    }

    override fun onCleared() {
        moviesList?.removeObserver(observer)
        super.onCleared()
    }
}