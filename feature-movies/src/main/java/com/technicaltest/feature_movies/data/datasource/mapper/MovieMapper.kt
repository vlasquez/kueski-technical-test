package com.technicaltest.feature_movies.data.datasource.mapper

import com.technicaltest.feature_movies.data.datasource.remote.model.MovieGenreResponse
import com.technicaltest.feature_movies.data.datasource.remote.model.MovieResponse
import com.technicaltest.feature_movies.domain.entity.Genre
import com.technicaltest.feature_movies.domain.entity.Movie

fun MovieResponse.toMovie() = Movie(
    movieId = this.id,
    title = this.title,
    originalTitle = this.originalTitle,
    posterPath = BASE_IMAGE_URL + this.posterPath,
    backdropPath = BASE_IMAGE_URL + this.backdropPath,
    releaseDate = this.releaseDate,
    overview = this.overview,
    popularity = this.popularity,
    voteCount = this.voteCount,
    voteAverage = this.voteAverage,
    genreIds = this.genreIds,
    isFavorite = false,
    runtime = this.runtime,
    genres = this.genres?.toGenreList()

)

fun List<MovieGenreResponse>.toGenreList(): List<Genre> = this.map { genre -> genre.toGenre() }

fun MovieGenreResponse.toGenre() = Genre(
    id = this.id,
    name = this.name
)

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"