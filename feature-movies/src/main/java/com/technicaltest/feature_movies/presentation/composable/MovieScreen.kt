package com.technicaltest.feature_movies.presentation.composable

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.technicaltest.design_system.R
import com.technicaltest.design_system.theme.AppTheme
import com.technicaltest.design_system.theme.navigation.NavigationItem
import com.technicaltest.design_system.theme.views.LoadingAnimation
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.presentation.MoviesViewModel
import com.technicaltest.feature_movies.presentation.composable.layout.MovieGridLayout
import com.technicaltest.feature_movies.presentation.composable.layout.MovieListLayout

@Composable
fun MovieScreen(isGridView: Boolean, navigationItem: NavigationItem) {
    val viewModel: MoviesViewModel = hiltViewModel()

    val viewState by viewModel.viewState.collectAsState()

    val movies = remember { mutableStateOf(emptyList<Movie>()) }

    LaunchedEffect(Unit) {
        when (navigationItem) {
            NavigationItem.PopularMovies -> viewModel.getPopularMovies()
            NavigationItem.PlayingNowMovies -> viewModel.getPlayingNowMovies()
            NavigationItem.FavoriteMovies -> {//viewModel.getFavoriteMovies()
            }
        }
    }
    when (viewState) {
        is MoviesViewModel.ViewState.Loading -> {
            LoadingAnimation()
        }

        is MoviesViewModel.ViewState.Success -> {
            movies.value = (viewState as MoviesViewModel.ViewState.Success).popularMovies
                ?: (viewState as MoviesViewModel.ViewState.Success).playingNowMovies ?: emptyList()
        }

        is MoviesViewModel.ViewState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (viewState as MoviesViewModel.ViewState.Error).message
                    ?: stringResource(id = R.string.generic_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    MoviesLayout(isGridView = isGridView, movies = movies.value, onMovieClick = {}) //MoviesLayout
}

@Composable
private fun MoviesLayout(isGridView: Boolean, movies: List<Movie>, onMovieClick: (Movie) -> Unit) {
    if (isGridView) {
        MovieGridLayout(movies = movies, onMovieClick = onMovieClick)
    } else {
        MovieListLayout(movies = movies, onMovieClick = onMovieClick)
    }
}

@Composable
@Preview
private fun MoviesPreview() {
    AppTheme {
        MovieScreen(
            isGridView = true,
            navigationItem = NavigationItem.PopularMovies
        )
    }
}