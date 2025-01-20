package id.haaweejee.test.alfagift.alfamovie.data.di

import id.haaweejee.test.alfagift.alfamovie.data.repository.MovieRepositoryImpl
import id.haaweejee.test.alfagift.alfamovie.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<MovieRepository> { MovieRepositoryImpl(get()) }
}