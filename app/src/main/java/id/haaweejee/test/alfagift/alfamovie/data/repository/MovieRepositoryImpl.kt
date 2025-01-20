package id.haaweejee.test.alfagift.alfamovie.data.repository

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.data.mapper.toDetailMovie
import id.haaweejee.test.alfagift.alfamovie.data.mapper.toListMovie
import id.haaweejee.test.alfagift.alfamovie.data.mapper.toListMovieReview
import id.haaweejee.test.alfagift.alfamovie.data.mapper.toListMovieVideo
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.service.MovieDBApiService
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class MovieRepositoryImpl(
    private val remote: MovieDBApiService
) : MovieRepository {
    override fun fetchDiscover(page: Int): Flow<NetworkResult<List<Movie>>> {
        return flow {
            try {
                // Attempt to get data from the API
                val response = remote.getMoviesDiscover(page)

                // Check if the response is successful
                if (response != null) {
                    emit(NetworkResult.ResultSuccess(response.toListMovie()))
                } else {
                    emit(
                        NetworkResult.ResultError(
                            404,
                            "Data not found"
                        )
                    )
                }
            } catch (e: ClientRequestException) {
                // Handle HTTP errors (like 401, 403, 404)
                emit(
                    NetworkResult.ResultError(
                        e.response.status.value,
                        e.message ?: "Client error"
                    )
                )

            } catch (e: ServerResponseException) {
                // Handles 5xx HTTP errors
                emit(
                    NetworkResult.ResultError(
                        e.response.status.value,
                        e.message ?: "Server error"
                    )
                )
            } catch (e: IOException) {
                // Handle network failures, like no internet
                emit(NetworkResult.ResultFailed(e))

            } catch (e: Exception) {
                // Handle unexpected exceptions
                emit(NetworkResult.ResultFailed(e))
            }
        }
    }

    override fun fetchDetail(movieId: String): Flow<NetworkResult<DetailMovie>> {
        return flow {
            try {
                // Attempt to get data from the API
                val response = remote.getMovieDetail(movieId)

                // Check if the response is successful
                if (response != null) {
                    emit(NetworkResult.ResultSuccess(response.toDetailMovie()))
                } else {
                    emit(
                        NetworkResult.ResultError(
                            404,
                            "Data not found"
                        )
                    )
                }
            } catch (e: ClientRequestException) {
                // Handle HTTP errors (like 401, 403, 404)
                emit(
                    NetworkResult.ResultError(
                        e.response.status.value,
                        e.message
                    )
                )

            } catch (e: ServerResponseException) {
                // Handles 5xx HTTP errors
                emit(
                    NetworkResult.ResultError(
                        e.response.status.value,
                        e.message ?: "Server error"
                    )
                )
            } catch (e: IOException) {
                // Handle network failures, like no internet
                emit(NetworkResult.ResultFailed(e))

            } catch (e: Exception) {
                // Handle unexpected exceptions
                emit(NetworkResult.ResultFailed(e))
            }
        }
    }

    override fun fetchReviews(movieId: String): Flow<NetworkResult<List<MovieReview>>> {
        return flow {
            try {
                // Attempt to get data from the API
                val response = remote.getMovieReview(movieId)

                // Check if the response is successful
                if (response != null) {
                    emit(NetworkResult.ResultSuccess(response.toListMovieReview()))
                } else {
                    emit(
                        NetworkResult.ResultError(
                            404,
                            "Data not found"
                        )
                    )
                }
            } catch (e: ClientRequestException) {
                // Handle HTTP errors (like 401, 403, 404)
                emit(
                    NetworkResult.ResultError(
                        e.response.status.value,
                        e.message ?: "Client error"
                    )
                )

            } catch (e: ServerResponseException) {
                // Handles 5xx HTTP errors
                emit(
                    NetworkResult.ResultError(
                        e.response.status.value,
                        e.message ?: "Server error"
                    )
                )
            } catch (e: IOException) {
                // Handle network failures, like no internet
                emit(NetworkResult.ResultFailed(e))

            } catch (e: Exception) {
                // Handle unexpected exceptions
                emit(NetworkResult.ResultFailed(e))
            }
        }
    }

    override fun fetchVideos(movieId: String): Flow<NetworkResult<List<MovieVideo>>> {
        return flow {
            try {
                // Attempt to get data from the API
                val response = remote.getMovieVideo(movieId)

                // Check if the response is successful
                if (response != null) {
                    emit(NetworkResult.ResultSuccess(response.toListMovieVideo()))
                } else {
                    emit(
                        NetworkResult.ResultError(
                            404,
                            "Data not found"
                        )
                    )
                }
            } catch (e: ClientRequestException) {
                // Handle HTTP errors (like 401, 403, 404)
                emit(
                    NetworkResult.ResultError(
                        e.response.status.value,
                        e.message ?: "Client error"
                    )
                )

            } catch (e: ServerResponseException) {
                // Handles 5xx HTTP errors
                emit(
                    NetworkResult.ResultError(
                        e.response.status.value,
                        e.message ?: "Server error"
                    )
                )
            } catch (e: IOException) {
                // Handle network failures, like no internet
                emit(NetworkResult.ResultFailed(e))

            } catch (e: Exception) {
                // Handle unexpected exceptions
                emit(NetworkResult.ResultFailed(e))
            }
        }
    }
}