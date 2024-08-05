package com.technicaltest.feature_movies.data.datasource.local

import com.technicaltest.feature_movies.domain.entity.Movie

interface MoviesLocalDataSource {
    suspend fun saveFavoriteMovie(movieId: Long)
    suspend fun getFavoriteMovies(): List<Movie>
    suspend fun deleteFavoriteMovie(movieId: Long)
}