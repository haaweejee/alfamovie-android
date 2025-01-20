package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import kotlinx.coroutines.flow.Flow

interface GetDetailMovieUseCase {
    operator fun invoke(id: Int): Flow<NetworkResult<DetailMovie>>
}