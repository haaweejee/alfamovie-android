package id.haaweejee.test.alfagift.alfamovie.domain.di

import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetDetailMovieUseCase
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetDetailMovieUseCaseImpl
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetDiscoverMoviesUseCase
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetDiscoverMoviesUseCaseImpl
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetMovieReviewsUseCase
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetMovieReviewsUseCaseImpl
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetMovieVideoUseCase
import id.haaweejee.test.alfagift.alfamovie.domain.usecase.GetMovieVideoUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetDiscoverMoviesUseCase> { GetDiscoverMoviesUseCaseImpl(get()) }
    factory<GetDetailMovieUseCase> { GetDetailMovieUseCaseImpl(get()) }
    factory<GetMovieReviewsUseCase> { GetMovieReviewsUseCaseImpl(get()) }
    factory<GetMovieVideoUseCase> { GetMovieVideoUseCaseImpl(get()) }
}