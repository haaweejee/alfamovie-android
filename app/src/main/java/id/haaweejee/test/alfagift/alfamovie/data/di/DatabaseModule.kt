package id.haaweejee.test.alfagift.alfamovie.data.di

import androidx.room.Room
import id.haaweejee.test.alfagift.alfamovie.data.source.local.MovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), MovieDatabase::class.java, "app_database").build()
    }
    single { get<MovieDatabase>().movieDao() }
}