package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import kotlinx.coroutines.flow.Flow

interface GetMovieReviewsUseCase {
    operator fun invoke(id: Int): Flow<NetworkResult<List<MovieReview>>>
}