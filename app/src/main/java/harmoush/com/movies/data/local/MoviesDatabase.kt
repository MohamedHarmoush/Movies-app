package harmoush.com.movies.data.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import harmoush.com.movies.data.models.FavoriteMovies
import harmoush.com.movies.data.models.Movie
import harmoush.com.movies.utils.Constants

@Database(entities = arrayOf(Movie::class,FavoriteMovies::class), version = 1, exportSchema = false)
abstract class MoviesDatabase() : RoomDatabase() {

    companion object {
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext
                        , MoviesDatabase::class.java
                        , Constants.DB_NAME
                    ).build()
                return INSTANCE as MoviesDatabase
            }
        }
    }

    abstract fun movieDao(): MovieDao
}