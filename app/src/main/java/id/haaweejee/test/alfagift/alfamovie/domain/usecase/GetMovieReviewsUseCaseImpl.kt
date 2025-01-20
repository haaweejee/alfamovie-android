package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieReviewsUseCaseImpl(
    private val repository: MovieRepository
) : GetMovieReviewsUseCase {
    override fun invoke(id: Int): Flow<NetworkResult<List<MovieReview>>> {
        return repository.fetchReviews(id)
    }
}