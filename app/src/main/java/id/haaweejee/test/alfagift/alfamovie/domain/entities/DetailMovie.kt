package id.haaweejee.test.alfagift.alfamovie.domain.entities

data class DetailMovie(
    val id: Int,
    val title: String,
    val poster: String,
    val backdrop: String,
    val releaseDate: String,
    val rating: Double,
    val overview: String,
    val budget: Long,
    val status: String,
    val genres: List<String>,
    val duration: Int
)
