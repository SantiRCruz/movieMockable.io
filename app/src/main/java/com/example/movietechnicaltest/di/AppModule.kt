package com.example.movietechnicaltest.di

import com.example.movietechnicaltest.core.Constants
import com.example.movietechnicaltest.data.rest.WebService
import com.example.movietechnicaltest.domain.movies.MoviesRepo
import com.example.movietechnicaltest.domain.movies.MoviesRepoImpl
import com.example.movietechnicaltest.presentation.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    viewModel { provideOrdersViewModel(get()) }
    single { provideMoviesRepository(get()) }
    single { provideWebService(get()) }
}

private fun provideOrdersViewModel(repository: MoviesRepoImpl): MoviesViewModel {
    return MoviesViewModel(repository)
}

private fun provideMoviesRepository(
    rest: WebService,
): MoviesRepoImpl {
    return MoviesRepoImpl(rest)
}

private fun provideWebService(retrofit: Retrofit): WebService {
    val client = retrofit.newBuilder().baseUrl(Constants.BASE_URL).build()
    return client.create(WebService::class.java)
}
