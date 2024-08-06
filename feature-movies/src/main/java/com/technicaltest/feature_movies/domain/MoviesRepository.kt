package com.technicaltest.feature_movies.domain

import com.technicaltest.feature_movies.domain.entity.Movie

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): Result<List<Movie>>

    suspend fun getNowPlayingMovies(
        page: Int,
        maximumDate: String
    ): Result<List<Movie>>

    suspend fun saveFavoriteMovie(movieId: Long)

    suspend fun getFavoriteMovies(): List<Movie>

    suspend fun deleteFavoriteMovie(movieId: Long)
}