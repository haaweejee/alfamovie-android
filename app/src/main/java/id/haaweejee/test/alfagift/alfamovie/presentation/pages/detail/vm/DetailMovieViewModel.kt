package id.haaweejee.test.alfagift.alfamovie.presentation.pages.detail.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetDetailMovieUseCase
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

class DetailMovieViewModel(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<DetailMovieUIState> =
        MutableStateFlow(DetailMovieUIState())
    val state: StateFlow<DetailMovieUIState> = _state

    fun setMovieID(id: Int) {
        _state.update {
            it.copy(
                id = id
            )
        }
    }

    fun getDetailMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            getDetailMovieUseCase.invoke(state.value.id)
                .map {
                    it.mapToViewState()
                }.onStart {
                    emit(ViewState.ViewLoading)
                }.collect { data ->
                    _state.update {
                        it.copy(
                            detailMovie = data
                        )
                    }
                }
        }
    }

    fun getMovieReviews() {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieReviewsUseCase.invoke(state.value.id)
                .map {
                    it.mapToViewState()
                }.onStart {
                    emit(ViewState.ViewLoading)
                }.collect { data ->
                    _state.update {
                        it.copy(
                            movieReviews = data
                        )
                    }
                }
        }
    }

    fun getMovieVideo() {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieVideoUseCase.invoke(state.value.id)
                .map {
                    it.mapToViewState()
                }.onStart {
                    emit(ViewState.ViewLoading)
                }.collect { data ->
                    _state.update {
                        it.copy(
                            movieVideo = data
                        )
                    }
                }
        }
    }
}