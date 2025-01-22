package id.haaweejee.test.alfagift.alfamovie.domain.usecase

import app.cash.turbine.test
import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import kotlin.test.Test

class GetMovieReviewsUseCaseImplTest {
    private val mockRepository = mockk<MovieRepository>()
    private val useCase = GetMovieReviewsUseCaseImpl(mockRepository)

    @Test
    fun `invoke should emit ResultSuccess when repository fetchReviews emits ResultSuccess`() =
        runTest {
            // Arrange
            val movieId = 1
            val reviews = listOf(
                MovieReview(id = "2123", author = "Author 1", content = "Review content 1"),
                MovieReview(id = "34123", author = "Author 2", content = "Review content 2")
            )
            val successResult = NetworkResult.ResultSuccess(reviews)

            coEvery { mockRepository.fetchReviews(movieId) } returns flowOf(successResult)

            // Act & Assert
            useCase(movieId).test {
                assertEquals(successResult, awaitItem()) // Verify emitted value
                awaitComplete() // Ensure Flow completes
            }
        }

    @Test
    fun `invoke should emit ResultError when repository fetchReviews emits ResultError`() =
        runTest {
            // Arrange
            val movieId = 1
            val errorResult = NetworkResult.ResultError(code = 404, message = "Reviews not found")

            coEvery { mockRepository.fetchReviews(movieId) } returns flowOf(errorResult)

            // Act & Assert
            useCase(movieId).test {
                assertEquals(errorResult, awaitItem()) // Verify emitted value
                awaitComplete() // Ensure Flow completes
            }
        }

    @Test
    fun `invoke should emit ResultFailed when repository fetchReviews emits ResultFailed`() =
        runTest {
            // Arrange
            val movieId = 1
            val exception = Exception("Network error")
            val failedResult = NetworkResult.ResultFailed(exception)

            coEvery { mockRepository.fetchReviews(movieId) } returns flowOf(failedResult)

            // Act & Assert
            useCase(movieId).test {
                assertEquals(failedResult, awaitItem()) // Verify emitted value
                awaitComplete() // Ensure Flow completes
            }
        }

}