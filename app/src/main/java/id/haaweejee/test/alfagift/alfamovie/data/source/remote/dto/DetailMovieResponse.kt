package id.haaweejee.test.alfagift.alfamovie.data.source.remote.dto


import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any? = null,
    @SerializedName("budget")
    val budget: Long? = null,
    @SerializedName("genres")
    val genres: List<Genre?>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("origin_country")
    val originCountry: List<String?>? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany?>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry?>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("revenue")
    val revenue: Int? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage?>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
) {
    data class Genre(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null
    )

    data class ProductionCompany(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("logo_path")
        val logoPath: String? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("origin_country")
        val originCountry: String? = null
    )

    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        val iso31661: String? = null,
        @SerializedName("name")
        val name: String? = null
    )

    data class SpokenLanguage(
        @SerializedName("english_name")
        val englishName: String? = null,
        @SerializedName("iso_639_1")
        val iso6391: String? = null,
        @SerializedName("name")
        val name: String? = null
    )
}