package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetDetailMovieUseCaseImpl(
    private val repository: MovieRepository
) : GetDetailMovieUseCase {
    override fun invoke(id: Int): Flow<NetworkResult<DetailMovie>> {
        return repository.fetchDetail(movieId = id)
    }
}