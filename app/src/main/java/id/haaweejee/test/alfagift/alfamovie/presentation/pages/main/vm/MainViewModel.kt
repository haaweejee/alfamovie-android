package id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetDetailMovieUseCase
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetDiscoverMoviesUseCase
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetMovieReviewsUseCase
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetMovieVideoUseCase
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
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<MainUIState> = MutableStateFlow(MainUIState())
    val state: StateFlow<MainUIState> = _state

    fun getDiscoverMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getDiscoverMoviesUseCase.invoke(1)
                .map {
                    it.mapToViewState()
                }.onStart {
                    emit(ViewState.ViewLoading)
                }.collect { data ->
                    _state.update {
                        it.copy(
                            discoverMovie = data
                        )
                    }
                }
        }
    }
}