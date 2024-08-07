package com.technicaltest.feature_movies.presentation.composable

import com.technicaltest.feature_movies.domain.entity.Movie

fun moviePreviewProvider() = listOf(
    Movie(
        movieId = 0,
        title = "The Godfather",
        originalTitle = "The Godfather",
        posterPath = "The Godfather",
        backdropPath = "The Godfather",
        releaseDate = "The Godfather",
        overview = "The Godfather",
        popularity = 1.0,
        voteCount = 1,
        voteAverage = 1.0,
        genreIds = listOf(1),
        isFavorite = false,
        runtime = 1,
        genres = listOf()
    ), Movie(
        movieId = 1,
        title = "The Godfather: Part II",
        originalTitle = "The Godfather: Part II",
        posterPath = "The Godfather: Part II",
        backdropPath = "The Godfather: Part II",
        releaseDate = "The Godfather: Part II",
        overview = "The Godfather: Part II",
        popularity = 1.0,
        voteCount = 1,
        voteAverage = 1.0,
        genreIds = listOf(1),
        isFavorite = false,
        runtime = 1,
        genres = listOf()
    )
)