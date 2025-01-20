package id.haaweejee.test.alfagift.alfamovie.domain.repository

import id.haaweejee.test.alfagift.alfamovie.data.common.NetworkResult
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscover(page: Int): Flow<NetworkResult<List<Movie>>>
    fun fetchDetail(movieId: Int): Flow<NetworkResult<DetailMovie>>
    fun fetchReviews(movieId: Int): Flow<NetworkResult<List<MovieReview>>>
    fun fetchVideos(movieId: Int): Flow<NetworkResult<List<MovieVideo>>>
}