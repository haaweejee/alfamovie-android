package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo
import kotlinx.coroutines.flow.Flow

interface GetMovieVideoUseCase {
    operator fun invoke(id: Int): Flow<NetworkResult<MovieVideo>>
}