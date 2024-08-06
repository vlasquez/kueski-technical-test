package com.technicaltest.feature_movies.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.presentation.MoviesViewModel
import com.technicaltest.feature_movies.presentation.composable.layout.Layout
import com.technicaltest.feature_movies.presentation.composable.layout.MovieGridLayout
import com.technicaltest.feature_movies.presentation.composable.layout.MovieListLayout

@Composable
fun MovieLayout(modifier: Modifier = Modifier) {
    val viewModel: MoviesViewModel = hiltViewModel()

    val movies by remember { mutableStateOf(emptyList<Movie>()) }

    var layout by remember { mutableStateOf(Layout.GRID) }

    when (layout) {
        Layout.GRID -> MovieGridLayout(movies = movies) {
            layout = Layout.LIST
        }

        Layout.LIST -> MovieListLayout(movies = movies) {
            layout = Layout.GRID
        }
    }
}

@Composable
@Preview
private fun MoviesPreview() {
    MovieLayout()
}