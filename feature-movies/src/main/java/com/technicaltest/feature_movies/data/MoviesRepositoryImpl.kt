package com.technicaltest.feature_movies.data

import com.technicaltest.feature_movies.data.datasource.local.MoviesLocalDataSource
import com.technicaltest.feature_movies.data.datasource.mapper.toMovie
import com.technicaltest.feature_movies.data.datasource.remote.MoviesRemoteDataSource
import com.technicaltest.feature_movies.domain.MoviesRepository
import com.technicaltest.feature_movies.domain.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            moviesRemoteDataSource.getPopularMovies(page = page).map { response ->
                response.results.map { movieResponse -> movieResponse.toMovie() }
            }
        }

    override suspend fun getNowPlayingMovies(
        page: Int,
        maximumDate: String
    ): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            moviesRemoteDataSource.getNowPlayingMovies(
                page = page,
                maximumDate = maximumDate
            ).map { response ->
                response.results.map { movieResponse -> movieResponse.toMovie() }
            }
        }


    override suspend fun getMovieDetail(movieId: String): Result<Movie> =
        withContext(Dispatchers.IO) {
            moviesRemoteDataSource.getMovieDetail(movieId = movieId).map { response ->
                response.toMovie()
            }
        }

    override suspend fun saveFavoriteMovie(movieId: Long) =
        moviesLocalDataSource.saveFavoriteMovie(movieId = movieId)

    override suspend fun getFavoriteMovies() =
        moviesLocalDataSource.getFavoriteMovies()

    override suspend fun deleteFavoriteMovie(movieId: Long) =
        moviesLocalDataSource.deleteFavoriteMovie(movieId = movieId)
}