package com.technicaltest.feature_movies.data.datasource.remote

import com.technicaltest.feature_movies.data.datasource.remote.model.GenericMovieResponse
import javax.inject.Inject

internal class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): Result<GenericMovieResponse> =
        moviesApi.getPopularMovies(page = page)


    override suspend fun getNowPlayingMovies(
        page: Int,
        minimumDate: String,
        maximumDate: String
    ): Result<GenericMovieResponse> =
        moviesApi.getNowPlayingMovies(
            page = page,
            minimumDate = minimumDate,
            maximumDate = maximumDate
        )
}