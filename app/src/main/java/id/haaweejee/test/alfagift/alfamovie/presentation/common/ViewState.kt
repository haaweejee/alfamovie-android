package id.haaweejee.test.alfagift.alfamovie.presentation.common

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult

sealed class ViewState<out T> {
    data object ViewEmpty : ViewState<Nothing>()
    data object ViewLoading : ViewState<Nothing>()
    data class ViewSuccess<out T>(val data: T) : ViewState<T>()
    data class ViewError(val title: String, val message: String) : ViewState<Nothing>()
    data class ViewFailed(val exception: Exception) : ViewState<Nothing>()
}

fun <T> NetworkResult<T>.mapToViewState(): ViewState<T> {
    return when (this) {
        is NetworkResult.ResultSuccess -> ViewState.ViewSuccess(this.data)
        is NetworkResult.ResultError -> ViewState.ViewError(
            title = when (code) {
                401 -> "Unauthorized"
                404 -> "Page Not Found"
                else -> "Something went wrong"
            },
            message = when (code) {
                401 -> "You Don't Have Permission to access this Page"
                404 -> "Page Not Found, Please try again."
                else -> "Something went wrong, Please try again."
            }
        )
        is NetworkResult.ResultFailed -> ViewState.ViewFailed(this.exception)
    }
}