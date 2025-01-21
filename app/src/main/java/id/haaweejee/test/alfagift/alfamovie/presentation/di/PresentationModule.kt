package id.haaweejee.test.alfagift.alfamovie.presentation.di

import id.haaweejee.test.alfagift.alfamovie.presentation.pages.detail.vm.DetailMovieViewModel
import id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::DetailMovieViewModel)
}