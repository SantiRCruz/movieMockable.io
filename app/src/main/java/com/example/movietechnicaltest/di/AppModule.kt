package com.example.movietechnicaltest.di

import android.app.Application
import com.example.movietechnicaltest.core.Constants
import com.example.movietechnicaltest.data.rest.WebService
import com.example.movietechnicaltest.domain.movies.MoviesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWebService(): WebService {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        rest: WebService,
        app: Application
    ): MoviesRepoImpl {
        return MoviesRepoImpl(rest, app)
    }

}