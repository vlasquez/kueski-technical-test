package com.technicaltest.feature_movies.data.datasource.remote

import com.technicaltest.feature_movies.data.datasource.remote.model.GenericMovieResponse
import com.technicaltest.feature_movies.data.datasource.remote.model.MovieResponse

interface MoviesRemoteDataSource {

    suspend fun getPopularMovies(
        page: Int
    ): Result<GenericMovieResponse>

    suspend fun getNowPlayingMovies(
        page: Int,
        maximumDate: String
    ): Result<GenericMovieResponse>

    suspend fun getMovieDetail(
        movieId: String
    ): Result<MovieResponse>
}