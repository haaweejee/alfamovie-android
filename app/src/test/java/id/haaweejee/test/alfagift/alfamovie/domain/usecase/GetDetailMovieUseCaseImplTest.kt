package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import app.cash.turbine.test
import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import kotlin.test.Test

class GetDetailMovieUseCaseImplTest {

    private val mockRepository = mockk<MovieRepository>()
    private val useCase = GetDetailMovieUseCaseImpl(mockRepository)

    @Test
    fun `invoke should return success when repository fetchDetail emits ResultSuccess`() = runTest {
        // Arrange
        val movieId = 1
        val detailMovie = DetailMovie(
            id = movieId, title = "Test Movie", overview = "Overview",
            poster = "",
            backdrop = "",
            releaseDate = "",
            rating = 0.0,
            budget = 0,
            status = "",
            genres = listOf("", ""),
            duration = 0
        )
        val successResult = NetworkResult.ResultSuccess(detailMovie)

        coEvery { mockRepository.fetchDetail(movieId) } returns flowOf(successResult)

        // Act & Assert
        useCase(movieId).test {
            assertEquals(successResult, awaitItem()) // Verify first emitted value
            awaitComplete() // Ensure Flow completes
        }
    }

    @Test
    fun `invoke should return error when repository fetchDetail emits ResultError`() = runTest {
        // Arrange
        val movieId = 1
        val errorResult = NetworkResult.ResultError(code = 404, message = "Not Found")

        coEvery { mockRepository.fetchDetail(movieId) } returns flowOf(errorResult)

        // Act & Assert
        useCase(movieId).test {
            assertEquals(errorResult, awaitItem()) // Verify first emitted value
            awaitComplete() // Ensure Flow completes
        }
    }

    @Test
    fun `invoke should return failed when repository fetchDetail emits ResultFailed`() = runTest {
        // Arrange
        val movieId = 1
        val failedResult = NetworkResult.ResultFailed(Exception("Network Failure"))

        coEvery { mockRepository.fetchDetail(movieId) } returns flowOf(failedResult)

        // Act & Assert
        useCase(movieId).test {
            assertEquals(failedResult, awaitItem()) // Verify first emitted value
            awaitComplete() // Ensure Flow completes
        }
    }
}