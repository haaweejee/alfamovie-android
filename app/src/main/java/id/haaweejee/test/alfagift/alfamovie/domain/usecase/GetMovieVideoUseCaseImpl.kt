package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class GetMovieVideoUseCaseImpl(private val repository: MovieRepository) : GetMovieVideoUseCase {
    override fun invoke(id: Int): Flow<NetworkResult<MovieVideo>> = flow {
        val result = repository.fetchVideos(id).firstOrNull()

        if (result !is NetworkResult.ResultSuccess) {
            emit(NetworkResult.ResultError(404, "Data not found"))
            return@flow
        } else {
            val data = result.data
            val trailer = data.filter { it.type == "Trailer" && it.site == "YouTube" }
            emit(NetworkResult.ResultSuccess(trailer.last()))
            return@flow
        }
    }
}