package com.technicaltest.feature_movies.presentation.composable.layout

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.technicaltest.design_system.theme.AppTheme
import com.technicaltest.design_system.theme.views.MovieItemView
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.presentation.composable.moviePreviewProvider

@Composable
fun MovieListLayout(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(movies) { movie ->
            MovieItemView(
                posterUrl = movie.posterPath,
                movieTitle = movie.title,
                moviePopularity = movie.popularity ?: 0.0,
                onItemClick = { onMovieClick(movie) }
            )
        }
    }
}

@Composable
@Preview
private fun MoviesPreview() {
    AppTheme {
        MovieListLayout(
            movies = moviePreviewProvider(),
            onMovieClick = {}
        )
    }
}