package com.technicaltest.feature_movies.data.datasource.remote

import com.technicaltest.feature_movies.data.datasource.remote.model.GenericMovieResponse

interface MoviesRemoteDataSource {

    suspend fun getPopularMovies(
        page: Int
    ): Result<GenericMovieResponse>

    suspend fun getNowPlayingMovies(
        page: Int,
        maximumDate: String
    ): Result<GenericMovieResponse>
}