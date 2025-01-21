package id.haaweejee.test.alfagift.alfamovie.data.source.remote.service

import id.haaweejee.test.alfagift.alfamovie.BuildConfig
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.DetailMovieResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MovieReviewsResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MovieVideosResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders

class MovieDBApiServiceImpl(
    private val service: HttpClient
) : MovieDBApiService {

    override suspend fun getMoviesDiscover(page: Int): MoviesResponse? =
        service.get {
            url(BuildConfig.BASE_URL + moviesDiscover)
            header(HttpHeaders.Authorization, "Bearer ${BuildConfig.ACCESS_TOKEN}")
            parameter("page", page)
        }.body()

    override suspend fun getMovieDetail(movieId: Int): DetailMovieResponse? =
        service.get {
            url(BuildConfig.BASE_URL + movieDetail + movieId)
            header(HttpHeaders.Authorization, "Bearer ${BuildConfig.ACCESS_TOKEN}")
        }.body()

    override suspend fun getMovieReview(movieId: Int): MovieReviewsResponse? =
        service.get {
            url(BuildConfig.BASE_URL + movieDetail + movieId + movieReviews)
            header(HttpHeaders.Authorization, "Bearer ${BuildConfig.ACCESS_TOKEN}")
        }.body()

    override suspend fun getMovieVideo(movieId: Int): MovieVideosResponse? =
        service.get {
            url(BuildConfig.BASE_URL + movieDetail + movieId + movieVideos)
            header(HttpHeaders.Authorization, "Bearer ${BuildConfig.ACCESS_TOKEN}")
        }.body()

}