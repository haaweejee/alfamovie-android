package id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.vm

import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie
import id.haaweejee.test.alfagift.alfamovie.presentation.common.ViewState

data class MainUIState(
    val page: Int = 1,
    val discoverMovie: ViewState<List<Movie>> = ViewState.ViewEmpty,
    val isRefreshing: Boolean = false
)
