package id.haaweejee.test.alfagift.alfamovie.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.haaweejee.test.alfagift.alfamovie.data.source.local.dao.MovieDao
import id.haaweejee.test.alfagift.alfamovie.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}