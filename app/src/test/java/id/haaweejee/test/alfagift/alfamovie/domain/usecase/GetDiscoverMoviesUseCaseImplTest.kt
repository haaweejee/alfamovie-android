package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import app.cash.turbine.test
import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import kotlin.test.Test

class GetDiscoverMoviesUseCaseImplTest {
    private val mockRepository = mockk<MovieRepository>()
    private val useCase = GetDiscoverMoviesUseCaseImpl(mockRepository)

    @Test
    fun `invoke should emit ResultSuccess when repository fetchDiscover emits ResultSuccess`() =
        runTest {
            // Arrange
            val page = 1
            val movies = listOf(
                Movie(id = 1, title = "Movie 1"),
                Movie(id = 2, title = "Movie 2")
            )
            val successResult = NetworkResult.ResultSuccess(movies)

            coEvery { mockRepository.fetchDiscover(page) } returns flowOf(successResult)

            // Act & Assert
            useCase(page).test {
                assertEquals(successResult, awaitItem()) // Verify emitted value
                awaitComplete() // Ensure Flow completes
            }
        }

    @Test
    fun `invoke should emit ResultError when repository fetchDiscover emits ResultError`() =
        runTest {
            // Arrange
            val page = 1
            val errorResult = NetworkResult.ResultError(code = 404, message = "Not Found")

            coEvery { mockRepository.fetchDiscover(page) } returns flowOf(errorResult)

            // Act & Assert
            useCase(page).test {
                assertEquals(errorResult, awaitItem()) // Verify emitted value
                awaitComplete() // Ensure Flow completes
            }
        }

    @Test
    fun `invoke should emit ResultFailed when repository fetchDiscover emits ResultFailed`() =
        runTest {
            // Arrange
            val page = 1
            val exception = Exception("Network error")
            val failedResult = NetworkResult.ResultFailed(exception)

            coEvery { mockRepository.fetchDiscover(page) } returns flowOf(failedResult)

            // Act & Assert
            useCase(page).test {
                assertEquals(failedResult, awaitItem()) // Verify emitted value
                awaitComplete() // Ensure Flow completes
            }
        }
}