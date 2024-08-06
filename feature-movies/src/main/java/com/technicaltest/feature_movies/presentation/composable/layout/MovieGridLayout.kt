package com.technicaltest.feature_movies.presentation.composable.layout

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.technicaltest.design_system.theme.AppTheme
import com.technicaltest.design_system.theme.views.MovieItemView
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.presentation.composable.moviePreviewProvider

@Composable
fun MovieGridLayout(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier,
    ) {
        items(movies) { movie ->
            MovieItemView(
                posterUrl = movie.posterPath,
                movieTitle = movie.title,
                moviePopularity = movie.popularity ?: 0.0,
                onItemClick = {
                    onMovieClick(movie)
                }
            )
        }
    }
}

@Composable
@Preview(Devices.NEXUS_9)
private fun MoviesPreview() {
    AppTheme {
        MovieGridLayout(
            movies = moviePreviewProvider(),
            onMovieClick = {}
        )
    }
}