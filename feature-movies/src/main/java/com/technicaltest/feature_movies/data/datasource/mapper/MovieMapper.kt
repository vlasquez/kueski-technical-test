package com.technicaltest.feature_movies.data.datasource.mapper

import com.technicaltest.feature_movies.data.datasource.remote.model.MovieResponse
import com.technicaltest.feature_movies.domain.entity.Movie

fun MovieResponse.toMovie() = Movie(
    movieId = this.id,
    title = this.title,
    originalTitle = this.originalTitle,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    overview = this.overview,
    popularity = this.popularity,
    voteCount = this.voteCount,
    voteAverage = this.voteAverage,
    genreIds = this.genreIds,
    isFavorite = false
)