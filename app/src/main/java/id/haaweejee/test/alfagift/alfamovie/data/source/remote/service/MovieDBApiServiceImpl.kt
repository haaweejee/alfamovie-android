package id.haaweejee.test.alfagift.alfamovie.data.source.remote.service

import id.haaweejee.test.alfagift.alfamovie.BuildConfig
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.DetailMovieResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MovieReviewsResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MovieVideosResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class MovieDBApiServiceImpl(
    private val service: HttpClient
) : MovieDBApiService {

    override suspend fun getMoviesDiscover(genreId: String?, page: Int): MoviesResponse =
        service.get {
            url(BuildConfig.BASE_URL + moviesDiscover)
            parameter("page", page)
        }.body()

    override suspend fun getMovieDetail(movieId: String): DetailMovieResponse =
        service.get {
            url(BuildConfig.BASE_URL + movieDetail + movieId)
        }.body()

    override suspend fun getMovieReview(movieId: String): MovieReviewsResponse =
        service.get {
            url(BuildConfig.BASE_URL + movieDetail + movieId + movieReviews)
        }.body()

    override suspend fun getMovieVideo(movieId: String): MovieVideosResponse =
        service.get {
            url(BuildConfig.BASE_URL + movieDetail + movieId + movieVideos)
        }.body()

}