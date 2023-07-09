package com.example.movietechnicaltest.domain.movies

import com.example.movietechnicaltest.data.models.BaseResponse
import com.example.movietechnicaltest.data.models.movies.MoviesResponse
import com.example.movietechnicaltest.data.rest.WebService

class MoviesRepoImpl(private val rest: WebService):MoviesRepo {
    override suspend fun getMovies(): BaseResponse<MoviesResponse> = rest.getMovies()
}