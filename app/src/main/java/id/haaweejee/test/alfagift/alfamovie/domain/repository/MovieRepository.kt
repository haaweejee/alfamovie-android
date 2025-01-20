package id.haaweejee.test.alfagift.alfamovie.domain.repository

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscover(page: Int): Flow<NetworkResult<List<Movie>>>
    fun fetchDetail(movieId: String): Flow<NetworkResult<DetailMovie>>
    fun fetchReviews(movieId: String): Flow<NetworkResult<List<MovieReview>>>
    fun fetchVideos(movieId: String): Flow<NetworkResult<List<MovieVideo>>>
}