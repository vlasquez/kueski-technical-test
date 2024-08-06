package com.technicaltest.feature_movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.domain.usecase.GetPlayingNowMoviesUseCase
import com.technicaltest.feature_movies.domain.usecase.GetPopularMoviesUseCase
import com.technicaltest.util.transformTodayDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPlayingNowMoviesUseCase: GetPlayingNowMoviesUseCase
) : ViewModel() {

    val viewState: StateFlow<ViewState> get() = _viewState

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)

    fun getPopularMovies(page: Int = 1) {
        viewModelScope.launch {
            getPopularMoviesUseCase(page = page)
                .onSuccess {
                    _viewState.value = ViewState.Success(
                        popularMovies = it,
                        playingNowMovies = null
                    )
                }.onFailure {
                    _viewState.value = ViewState.Error(message = it.message)
                }
        }
    }

    fun getPlayingNowMovies(page: Int = 1) {
        viewModelScope.launch {
            getPlayingNowMoviesUseCase(page = page, maximumDate = transformTodayDate())
                .onSuccess {
                    _viewState.value = ViewState.Success(
                        popularMovies = null,
                        playingNowMovies = it
                    )
                }.onFailure {
                    _viewState.value = ViewState.Error(message = it.message)
                }
        }
    }

    sealed class ViewState {
        data object Loading : ViewState()
        data class Success(
            val popularMovies: List<Movie>?,
            val playingNowMovies: List<Movie>?
        ) : ViewState()

        data class Error(val message: String?) : ViewState()
    }
}