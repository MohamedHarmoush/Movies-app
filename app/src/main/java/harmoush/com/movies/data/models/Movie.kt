package harmoush.com.movies.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Movie_Table")
data class Movie(
    @PrimaryKey
    val id: Int,
    @SerializedName("vote_count")
    val voteCount: Int?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    var favorite: Boolean = false
) : Serializable
