package id.haaweejee.test.alfagift.alfamovie.data.mapper

import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.DetailMovieResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MovieReviewsResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MovieVideosResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto.MoviesResponse
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.service.imageUrl
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo


fun MoviesResponse.toListMovie(): List<Movie> {
    return this.results?.map {
        it.toMovie()
    }.orEmpty()
}

fun MoviesResponse.Result.toMovie(): Movie {
    return Movie(
        id = id ?: 0,
        title = title.orEmpty(),
        poster = imageUrl + posterPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        rating = voteAverage ?: 0.0
    )
}

fun DetailMovieResponse.toDetailMovie(): DetailMovie {
    return DetailMovie(
        id = id ?: 0,
        title = title.orEmpty(),
        poster = imageUrl + posterPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        rating = voteAverage ?: 0.0,
        overview = overview.orEmpty(),
        genres = genres?.map { it?.name.orEmpty() }.orEmpty(),
        duration = runtime ?: 0,
        backdrop = imageUrl + backdropPath.orEmpty(),
        budget = budget ?: 0,
        status = status.orEmpty()
    )
}

fun MovieReviewsResponse.toListMovieReview(): List<MovieReview> {
    return this.results?.map {
        it.toMovieReview()
    }.orEmpty()
}

fun MovieReviewsResponse.Result.toMovieReview(): MovieReview {
    return MovieReview(
        author = author.orEmpty(),
        content = content.orEmpty(),
        avatar = imageUrl + authorDetails?.avatarPath.orEmpty(),
        id = id.orEmpty(),
        contentCreated = createdAt.orEmpty()
    )
}

fun MovieVideosResponse.toListMovieVideo(): List<MovieVideo> {
    return this.results?.map {
        it.toMovieVideo()
    }.orEmpty()
}

fun MovieVideosResponse.Result.toMovieVideo(): MovieVideo {
    return MovieVideo(
        key = key.orEmpty(),
        type = type.orEmpty(),
        site = site.orEmpty()
    )
}