package com.example.movietechnicaltest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movietechnicaltest.data.models.BaseResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import com.example.movietechnicaltest.core.Result
import com.example.movietechnicaltest.data.models.movies.MoviesResponse
import com.example.movietechnicaltest.domain.movies.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MoviesRepo) : ViewModel() {

    fun getMovies(): StateFlow<Result<BaseResponse<MoviesResponse>>> = flow {
        kotlin.runCatching {
            repo.getMovies()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

}

class MoviesViewModelFactory(private val repo: MoviesRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MoviesRepo::class.java).newInstance(repo)

    }
}