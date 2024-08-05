package com.technicaltest.feature_movies.data.datasource.local

import com.technicaltest.feature_movies.domain.entity.Movie

class MoviesLocalDataSourceImpl : MoviesLocalDataSource {

    override suspend fun saveFavoriteMovie(movieId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteMovie(movieId: Long) {
        TODO("Not yet implemented")
    }

}