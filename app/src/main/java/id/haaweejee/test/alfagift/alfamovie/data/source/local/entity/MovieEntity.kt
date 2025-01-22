package id.haaweejee.test.alfagift.alfamovie.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val poster: String,
    val releaseDate: String,
    val rating: Double,
    val overview: String
)