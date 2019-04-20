package harmoush.com.movies.data.remote

import harmoush.com.movies.data.models.PlayingMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") page: Int): Call<PlayingMoviesResponse>
}