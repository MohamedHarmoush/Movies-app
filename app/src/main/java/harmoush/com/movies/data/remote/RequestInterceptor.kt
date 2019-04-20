package harmoush.com.movies.data.remote

import harmoush.com.movies.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest: Request = chain.request()

        val url: HttpUrl = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.MOVIE_DB_API_Key)
            .build()

        val request:Request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}