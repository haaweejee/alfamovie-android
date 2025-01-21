package id.haaweejee.test.alfagift.alfamovie.presentation.common

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult

sealed class ViewState<out T> {
    data object ViewEmpty : ViewState<Nothing>()
    data object ViewLoading : ViewState<Nothing>()
    data class ViewSuccess<out T>(val data: T) : ViewState<T>()
    data class ViewError(val message: String) : ViewState<Nothing>()
    data class ViewFailed(val exception: Exception) : ViewState<Nothing>()
}

fun <T> NetworkResult<T>.mapToViewState(): ViewState<T> {
    return when (this) {
        is NetworkResult.ResultSuccess -> ViewState.ViewSuccess(this.data)
        is NetworkResult.ResultError -> ViewState.ViewError("Network error: $message (Code: $code)")
        is NetworkResult.ResultFailed -> ViewState.ViewFailed(this.exception)
    }
}