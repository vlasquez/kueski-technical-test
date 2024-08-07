package com.technicaltest.feature_movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {
    val viewState: StateFlow<ViewState> get() = _viewState

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            getMovieDetailUseCase(movieId = movieId)
                .onSuccess {
                    _viewState.value = ViewState.Success(movie = it)
                }.onFailure {
                    _viewState.value = ViewState.Error(message = it.message)
                }
        }
    }

    sealed class ViewState {
        data object Loading : ViewState()

        data class Success(val movie: Movie) : ViewState()

        data class Error(val message: String?) : ViewState()
    }
}