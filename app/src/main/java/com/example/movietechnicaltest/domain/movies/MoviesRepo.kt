package com.example.movietechnicaltest.domain.movies

import com.example.movietechnicaltest.data.models.BaseResponse
import com.example.movietechnicaltest.data.models.movies.MoviesResponse

interface MoviesRepo {

    suspend fun getMovies(): BaseResponse<MoviesResponse>
}