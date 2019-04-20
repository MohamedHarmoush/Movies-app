package harmoush.com.movies.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteMovies(@PrimaryKey val movieId:Int)