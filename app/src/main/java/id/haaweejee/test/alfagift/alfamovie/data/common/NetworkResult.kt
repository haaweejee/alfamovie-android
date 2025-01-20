package id.haaweejee.test.alfagift.alfamovie.data.common

sealed class NetworkResult<out T> {
    data class ResultSuccess<out T>(val data: T) : NetworkResult<T>()
    data class ResultFailed(val exception: Exception) : NetworkResult<Nothing>()
    data class ResultError(val code: Int, val message: String, val throwable: Throwable? = null) :
        NetworkResult<Nothing>()
}