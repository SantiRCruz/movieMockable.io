package com.example.movietechnicaltest.data.rest

import com.example.movietechnicaltest.core.Constants
import com.example.movietechnicaltest.data.models.BaseResponse
import com.example.movietechnicaltest.data.models.movies.MoviesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface WebService {

    @GET(MOVIES)
    suspend fun getMovies(): BaseResponse<MoviesResponse>


    companion object {
        internal const val MOVIES = "movies/"
    }
}

object RetrofitClient{
    val webService: WebService by lazy {

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
}