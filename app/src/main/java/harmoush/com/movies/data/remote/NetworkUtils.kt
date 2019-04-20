package harmoush.com.movies.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import harmoush.com.movies.data.models.Movie
import harmoush.com.movies.data.models.PlayingMoviesResponse
import harmoush.com.movies.utils.Constants
import harmoush.com.movies.utils.ResponseCallback
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {

    companion object {
        private var serviceInstance: ApiService? = null

        private fun getApiServiceInstance(): ApiService {

            val tempInstance = serviceInstance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                serviceInstance = retrofit().create(ApiService::class.java)
                return serviceInstance as ApiService
            }
        }

        private fun retrofit(): Retrofit {

            val gson: Gson = GsonBuilder().setLenient().create()

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(RequestInterceptor())
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
            return retrofit
        }

        fun getNowPlayingMovies(page: Int, callback: ResponseCallback<List<Movie>>) {
            getApiServiceInstance().getNowPlayingMovies(page).enqueue(object : Callback<PlayingMoviesResponse> {

                override fun onFailure(call: Call<PlayingMoviesResponse>, t: Throwable) {
                    val message = t.message

                    when {
                        message != null -> callback.onError(message)
                    }
                }

                override fun onResponse(call: Call<PlayingMoviesResponse>, response: Response<PlayingMoviesResponse>) {

                    when {
                        response.isSuccessful -> response.body()?.results?.let { callback.onSuccess(it) }
                        else -> callback.onError(response.message())
                    }
                }
            })
        }
    }


}