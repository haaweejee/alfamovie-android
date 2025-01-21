package id.haaweejee.test.alfagift.alfamovie.data.source.remote.service

import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.DetailMovieResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MovieReviewsResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MovieVideosResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MoviesResponse

interface MovieDBApiService {
    suspend fun getMoviesDiscover(page: Int): MoviesResponse?
    suspend fun getMovieDetail(movieId: Int): DetailMovieResponse?
    suspend fun getMovieReview(movieId: Int): MovieReviewsResponse?
    suspend fun getMovieVideo(movieId: Int): MovieVideosResponse?
}