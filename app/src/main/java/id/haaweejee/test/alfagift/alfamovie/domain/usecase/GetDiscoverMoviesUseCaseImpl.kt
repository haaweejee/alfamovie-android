package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetDiscoverMoviesUseCaseImpl(
    private val repository: MovieRepository
) : GetDiscoverMoviesUseCase {
    override fun invoke(page: Int): Flow<NetworkResult<List<Movie>>> {
        return repository.fetchDiscover(page)
    }

}