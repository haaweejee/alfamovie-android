package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import app.cash.turbine.test
import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import kotlin.test.Test

class GetMovieVideoUseCaseImplTest {
    private val mockRepository = mockk<MovieRepository>()
    private val useCase = GetMovieVideoUseCaseImpl(mockRepository)

    @Test
    fun `invoke should emit ResultSuccess with Trailer video when repository fetchVideos emits ResultSuccess`() =
        runTest {
            // Arrange
            val movieId = 1
            val movieVideos = listOf(
                MovieVideo(key = "abc123", site = "YouTube", type = "Trailer"),
                MovieVideo(key = "def456", site = "YouTube", type = "Teaser")
            )
            val successResult = NetworkResult.ResultSuccess(movieVideos)

            coEvery { mockRepository.fetchVideos(movieId) } returns flowOf(successResult)

            // Act & Assert
            useCase.invoke(movieId).test {
                val expectedVideo = MovieVideo(key = "abc123", site = "YouTube", type = "Trailer")
                assertEquals(NetworkResult.ResultSuccess(expectedVideo), awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `invoke should emit ResultSuccess with default MovieVideo when no Trailer or Teaser is found`() =
        runTest {
            // Arrange
            val movieId = 1
            val movieVideos = listOf(
                MovieVideo(key = "xyz789", site = "Vimeo", type = "Featurette")
            )
            val successResult = NetworkResult.ResultSuccess(movieVideos)

            coEvery { mockRepository.fetchVideos(movieId) } returns flowOf(successResult)

            // Act & Assert
            useCase.invoke(movieId).test {
                val expectedVideo = MovieVideo(key = "", site = "", type = "")
                assertEquals(NetworkResult.ResultSuccess(expectedVideo), awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `invoke should emit ResultError when repository fetchVideos emits non-success result`() =
        runTest {
            // Arrange
            val movieId = 1
            val errorResult = NetworkResult.ResultError(code = 404, message = "Videos not found")

            coEvery { mockRepository.fetchVideos(movieId) } returns flowOf(errorResult)

            // Act & Assert
            useCase.invoke(movieId).test {
                assertEquals(NetworkResult.ResultError(404, "Data not found"), awaitItem())
                awaitComplete()
            }
        }
}