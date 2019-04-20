package harmoush.com.movies.utils

interface ResponseCallback<T> {

    fun onSuccess(response: T)
    fun onError(message: String)
}