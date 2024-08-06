package com.technicaltest.feature_movies.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.technicaltest.design_system.theme.AppTheme
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.presentation.MoviesViewModel
import com.technicaltest.feature_movies.presentation.composable.layout.MovieGridLayout
import com.technicaltest.feature_movies.presentation.composable.layout.MovieListLayout

@Composable
fun MovieScreen(modifier: Modifier, isGridView: Boolean) {
    val viewModel: MoviesViewModel = hiltViewModel()

    val viewState by viewModel.viewState.collectAsState()

    val movies = remember { mutableStateOf(emptyList<Movie>()) }

    LaunchedEffect(Unit) {
        viewModel.getPopularMovies()
    }
    if (isGridView) {
        MovieGridLayout(modifier = modifier, movies = movies.value) {}
    } else {
        MovieListLayout(modifier = modifier, movies = movies.value) {}
    }

    when (viewState) {
        is MoviesViewModel.ViewState.Loading -> {

        }

        is MoviesViewModel.ViewState.Success -> {
            movies.value = (viewState as MoviesViewModel.ViewState.Success).popularMovies!!
        }

        is MoviesViewModel.ViewState.Error -> {

        }
    }
}

@Composable
@Preview
private fun MoviesPreview() {
    AppTheme {
        MovieScreen(modifier = Modifier, isGridView = true)
    }
}