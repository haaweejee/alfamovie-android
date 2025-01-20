package id.haaweejee.test.alfagift.alfamovie.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MovieVideosResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("results")
    val results: List<Result?>? = null
) {
    data class Result(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("iso_3166_1")
        val iso31661: String? = null,
        @SerializedName("iso_639_1")
        val iso6391: String? = null,
        @SerializedName("key")
        val key: String? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("official")
        val official: Boolean? = null,
        @SerializedName("published_at")
        val publishedAt: String? = null,
        @SerializedName("site")
        val site: String? = null,
        @SerializedName("size")
        val size: Int? = null,
        @SerializedName("type")
        val type: String? = null
    )
}