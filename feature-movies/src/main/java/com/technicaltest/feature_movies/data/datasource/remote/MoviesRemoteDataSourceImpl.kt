package com.technicaltest.feature_movies.data.datasource.remote

import com.technicaltest.feature_movies.data.datasource.remote.model.GenericMovieResponse

class MoviesRemoteDataSourceImpl : MoviesRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): Result<GenericMovieResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getNowPlayingMovies(
        page: Int,
        minimumDate: String,
        maximumDate: String
    ): Result<GenericMovieResponse> {
        TODO("Not yet implemented")
    }
}