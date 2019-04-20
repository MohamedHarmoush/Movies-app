package harmoush.com.movies.data.models

import com.google.gson.annotations.SerializedName

data class PlayingMoviesResponse(
    val results: List<Movie>
    , val page: Int
    , @SerializedName("total_results") val totalResults: Int
    , @SerializedName("total_pages") val totalPages: Int
)