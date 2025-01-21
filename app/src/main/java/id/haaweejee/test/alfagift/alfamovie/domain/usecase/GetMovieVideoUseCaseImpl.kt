package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetMovieVideoUseCaseImpl(private val repository: MovieRepository) : GetMovieVideoUseCase {
    override fun invoke(id: Int): Flow<NetworkResult<MovieVideo>> = flow {
        val result = repository.fetchVideos(id).first()

        if (result !is NetworkResult.ResultSuccess) {
            emit(NetworkResult.ResultError(404, "Data not found"))
            return@flow
        } else {
            val data = result.data
            val trailer = data.find { it.type == "Trailer" || it.type == "Teaser" } ?: MovieVideo(
                key = "",
                site = "",
                type = ""
            )
            emit(NetworkResult.ResultSuccess(trailer))
            return@flow
        }
    }
}