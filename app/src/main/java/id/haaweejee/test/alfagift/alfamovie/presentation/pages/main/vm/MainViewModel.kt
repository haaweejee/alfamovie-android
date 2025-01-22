package id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetDiscoverMoviesUseCase
import id.haaweejee.test.alfagift.alfamovie.presentation.common.ViewState
import id.haaweejee.test.alfagift.alfamovie.presentation.common.mapToViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<MainUIState> = MutableStateFlow(MainUIState())
    val state: StateFlow<MainUIState> = _state

    fun setDiscoverMoviePage(page: Int) {
        _state.update {
            it.copy(
                page = page
            )
        }
    }

    fun getDiscoverMovies(
        isPullRefresh: Boolean = false
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            getDiscoverMoviesUseCase.invoke(1)
                .map {
                    it.mapToViewState()
                }.onStart {
                    emit(ViewState.ViewLoading)
                    if (isPullRefresh) {
                        _state.update {
                            it.copy(
                                isRefreshing = true
                            )
                        }
                    }
                }.collect { data ->
                    _state.update {
                        it.copy(
                            discoverMovie = data,
                            isRefreshing = false
                        )
                    }
                }
        }
    }

    fun getNextPageDiscoverMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getDiscoverMoviesUseCase.invoke(state.value.page)
                .map {
                    it.mapToViewState()
                }.onStart {
                    emit(ViewState.ViewLoading)
                }.collect { data ->
                    _state.update {
                        it.copy(
                            discoverMovie = if (state.value.page < 1) {
                                data
                            } else {
                                val currentData = it.discoverMovie as? ViewState.ViewSuccess
                                val newData = data as? ViewState.ViewSuccess
                                val combinedData =
                                    if (currentData != newData) {
                                        currentData?.data.orEmpty() + newData?.data.orEmpty()
                                    } else {
                                        currentData?.data.orEmpty()
                                    }
                                ViewState.ViewSuccess(combinedData)
                            }
                        )
                    }
                }
        }
    }
}