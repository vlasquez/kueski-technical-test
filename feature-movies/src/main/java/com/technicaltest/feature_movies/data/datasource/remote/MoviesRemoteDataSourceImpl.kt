package com.technicaltest.feature_movies.data.datasource.remote

import com.technicaltest.feature_movies.data.datasource.remote.model.GenericMovieResponse
import com.technicaltest.feature_movies.data.datasource.remote.model.MovieResponse
import javax.inject.Inject

internal class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): Result<GenericMovieResponse> =
        moviesApi.getPopularMovies(page = page)


    override suspend fun getNowPlayingMovies(
        page: Int,
        maximumDate: String
    ): Result<GenericMovieResponse> =
        moviesApi.getNowPlayingMovies(
            page = page,
            minimumDate = MINIMUM_DATE,
            maximumDate = maximumDate
        )


    override suspend fun getMovieDetail(movieId: String): Result<MovieResponse> =
        moviesApi.getMovieDetail(movieId = movieId)
}

const val MINIMUM_DATE = "1950-01-01"
