package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface GetDiscoverMoviesUseCase {
    operator fun invoke(page: Int): Flow<NetworkResult<List<Movie>>>
}