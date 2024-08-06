package com.technicaltest.feature_movies.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.technicaltest.design_system.theme.AppTheme
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.presentation.MoviesViewModel
import com.technicaltest.feature_movies.presentation.composable.layout.Layout
import com.technicaltest.feature_movies.presentation.composable.layout.MovieGridLayout
import com.technicaltest.feature_movies.presentation.composable.layout.MovieListLayout

@Composable
fun MovieScreen(modifier: Modifier) {
    val viewModel: MoviesViewModel = hiltViewModel()

    val viewState by viewModel.viewState.collectAsState()

    val movies = remember { mutableStateOf(emptyList<Movie>()) }

    var layout by remember { mutableStateOf(Layout.GRID) }

    LaunchedEffect(Unit) {
        viewModel.getPopularMovies()
    }

    when (layout) {
        Layout.GRID -> MovieGridLayout(modifier = modifier, movies = movies.value) {
            layout = Layout.LIST
        }

        Layout.LIST -> MovieListLayout(modifier = modifier, movies = movies.value) {
            layout = Layout.GRID
        }
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
        MovieScreen(modifier = Modifier)
    }
}