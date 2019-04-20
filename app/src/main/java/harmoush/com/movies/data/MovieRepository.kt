package harmoush.com.movies.data

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import harmoush.com.movies.data.local.MovieDao
import harmoush.com.movies.data.local.MoviesDatabase
import harmoush.com.movies.data.models.Movie
import harmoush.com.movies.data.models.PlayingMoviesResponse
import harmoush.com.movies.data.remote.NetworkUtils
import harmoush.com.movies.utils.Constants
import harmoush.com.movies.utils.ResponseCallback
import harmoush.com.movies.utils.ResponseStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MovieRepository(applicationContext: Application) {

    private val TAG: String? = MovieRepository::class.simpleName

    private val movieDao: MovieDao = MoviesDatabase.getInstance(applicationContext).movieDao()

    private lateinit var moviesList: LiveData<List<Movie>>

    fun addFavoriteMovie(markFavorite: Boolean, movieId: Int) {

        val executor: Executor = Executors.newSingleThreadExecutor()
        executor.execute { movieDao.addFavoriteMovie(markFavorite, movieId) }
    }

    private fun deleteAllMovies() {
        val executor: Executor = Executors.newSingleThreadExecutor()
        executor.execute { movieDao.deleteAll() }
    }

    private fun insertDummyMovies() {
        val dummyData = DummyData().generateDummyMovies()

        val executor: Executor = Executors.newSingleThreadExecutor()
        executor.execute { movieDao.insertAll(dummyData) }
    }

    fun getAllMovies(onRefresh: Boolean): LiveData<List<Movie>> {

        if (onRefresh) {
            deleteAllMovies()
            getMoviesFromApi()

        }
        return movieDao.getAllMovies()
    }

    var message: MutableLiveData<ResponseStatus> = MutableLiveData()

    private fun getMoviesFromApi() {

        NetworkUtils.getNowPlayingMovies(1, object : ResponseCallback<List<Movie>> {
            override fun onSuccess(response: List<Movie>) {
                InsertMoviesAsyncTask(this@MovieRepository).execute(response)
            }

            override fun onError(message: String) {

                val status = ResponseStatus(message,false)
                this@MovieRepository.message.value = status

            }
        })
    }

    fun filterSortMoviesBy(SORT_ATTRIBUTE: Int, FILTER_ATTRIBUTE: Int): LiveData<List<Movie>> {

        moviesList = when {
            SORT_ATTRIBUTE != Constants.OPTION_NONE && FILTER_ATTRIBUTE != Constants.OPTION_NONE -> {
                movieDao.filterSortMoviesDistinctLiveData(getFilterValue(FILTER_ATTRIBUTE), SORT_ATTRIBUTE)
            }
            FILTER_ATTRIBUTE == Constants.OPTION_NONE -> movieDao.sortMoviesDistinctLiveData(SORT_ATTRIBUTE)
            else -> movieDao.filterMoviesDistinctLiveData(getFilterValue(FILTER_ATTRIBUTE))
        }
        return moviesList
    }

    private fun getFilterValue(FILTER_ATTRIBUTE: Int) = FILTER_ATTRIBUTE == Constants.OPTION_ONE

    class InsertMoviesAsyncTask(private val movieRepository: MovieRepository) : AsyncTask<List<Movie>, Void, Void>() {

        override fun doInBackground(vararg params: List<Movie>): Void? {

            movieRepository.movieDao.insertAll(params[0])
            movieRepository.movieDao.updateFavoriteMovies()

            return null
        }

        override fun onPostExecute(result: Void?) {
            movieRepository.moviesList = movieRepository.movieDao.getAllMovies()
        }

    }
}