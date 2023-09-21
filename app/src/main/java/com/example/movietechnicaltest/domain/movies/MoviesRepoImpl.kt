package com.example.movietechnicaltest.domain.movies

import android.app.Application
import com.example.movietechnicaltest.data.models.BaseResponse
import com.example.movietechnicaltest.data.models.movies.MoviesResponse
import com.example.movietechnicaltest.data.rest.WebService
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val rest: WebService,
    private val appContext: Application
) : MoviesRepo {
    override suspend fun getMovies(): BaseResponse<MoviesResponse> = rest.getMovies()
}