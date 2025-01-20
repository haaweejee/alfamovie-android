package id.haaweejee.test.alfagift.alfamovie.data.source.remote.service

import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.DetailMovieResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MovieReviewsResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MovieVideosResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MoviesResponse

interface MovieDBApiService {
    suspend fun getMoviesDiscover(genreId: String? = "", page: Int): MoviesResponse
    suspend fun getMovieDetail(movieId: String): DetailMovieResponse
    suspend fun getMovieReview(movieId: String): MovieReviewsResponse
    suspend fun getMovieVideo(movieId: String): MovieVideosResponse
}