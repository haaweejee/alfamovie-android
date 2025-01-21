package id.haaweejee.test.alfagift.alfamovie.presentation.pages.detail.vm

import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo
import id.haaweejee.test.alfagift.alfamovie.presentation.common.ViewState

data class DetailMovieUIState(
    val id: Int = 0,
    val detailMovie: ViewState<DetailMovie> = ViewState.ViewEmpty,
    val movieReviews: ViewState<List<MovieReview>> = ViewState.ViewEmpty,
    val movieVideo: ViewState<MovieVideo> = ViewState.ViewEmpty
)
