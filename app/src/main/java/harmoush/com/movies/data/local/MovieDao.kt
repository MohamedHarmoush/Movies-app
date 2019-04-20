package harmoush.com.movies.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import harmoush.com.movies.data.models.Movie
import harmoush.com.movies.utils.Constants

@Dao
abstract class MovieDao {

    @Query("select * from movie_table order by voteAverage desc")
    abstract fun getAllMovies(): LiveData<List<Movie>>

    @Insert
    abstract fun insertAll(movies: List<Movie>)

    @Query("update movie_table set favorite = 1 where id in (select * from favoritemovies) ")
    abstract fun updateFavoriteMovies()

    @Query("delete from movie_table")
    abstract fun deleteAll()

    @Query("update movie_table set favorite = :markFavorite where id = :movieId")
    protected abstract fun addMovieToFavoriteMovies(markFavorite: Boolean, movieId: Int)

    @Query("insert or replace into FavoriteMovies values (:movieId) ")
    protected abstract fun addMovieToFavoriteMovies(movieId: Int)

    @Query("delete from FavoriteMovies where movieId = :movieId ")
    protected abstract fun removeMovieFromFavoriteMovies(movieId: Int)

    fun addFavoriteMovie(markFavorite: Boolean, movieId: Int) {
        addMovieToFavoriteMovies(markFavorite, movieId)

        when {
            markFavorite -> addMovieToFavoriteMovies(movieId)
            else -> removeMovieFromFavoriteMovies(movieId)
        }
    }

    @Query("select * from movie_table where favorite = :liked")
    protected abstract fun filterMovies(liked: Boolean): LiveData<List<Movie>>

    fun filterMoviesDistinctLiveData(liked: Boolean): LiveData<List<Movie>> = filterMovies(liked)

    @Query("select * from movie_table where favorite = :liked order by title desc")
    protected abstract fun filterMoviesSortByTitle(liked: Boolean): LiveData<List<Movie>>

    @Query("select * from movie_table where favorite = :liked order by voteAverage desc")
    protected abstract fun filterMoviesSortByVote(liked: Boolean): LiveData<List<Movie>>

    fun filterSortMoviesDistinctLiveData(liked: Boolean, SORT_ATTRIBUTE: Int): LiveData<List<Movie>> = when {
        SORT_ATTRIBUTE == Constants.OPTION_ONE -> filterMoviesSortByTitle(liked)
        else -> filterMoviesSortByVote(liked)
    }

    @Query("select * from movie_table order by voteAverage desc")
    protected abstract fun sortMoviesByVote(): LiveData<List<Movie>>

    @Query("select * from movie_table order by title desc")
    protected abstract fun sortMoviesByTitle(): LiveData<List<Movie>>

    fun sortMoviesDistinctLiveData(SORT_ATTRIBUTE: Int): LiveData<List<Movie>> = when {
        SORT_ATTRIBUTE == Constants.OPTION_ONE -> sortMoviesByTitle()
        else -> sortMoviesByVote()
    }


}