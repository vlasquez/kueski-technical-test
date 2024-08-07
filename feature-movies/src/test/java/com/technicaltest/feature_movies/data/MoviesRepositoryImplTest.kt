package com.technicaltest.feature_movies.data

import com.technicaltest.feature_movies.data.datasource.local.MoviesLocalDataSource
import com.technicaltest.feature_movies.data.datasource.remote.MoviesRemoteDataSource
import com.technicaltest.feature_movies.data.datasource.remote.model.GenericMovieResponse
import com.technicaltest.feature_movies.data.datasource.remote.model.MovieResponse
import com.technicaltest.feature_movies.domain.entity.Movie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MoviesRepositoryImplTest {

    @MockK
    private lateinit var moviesRemoteDataSource: MoviesRemoteDataSource

    @MockK
    private lateinit var moviesLocalDataSource: MoviesLocalDataSource

    private lateinit var moviesRepositoryImpl: MoviesRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        moviesRepositoryImpl = MoviesRepositoryImpl(
            moviesRemoteDataSource = moviesRemoteDataSource,
            moviesLocalDataSource = moviesLocalDataSource
        )
    }

    @Test
    fun `GIVEN a page WHEN getPopularMovies THEN return a response with a list of movies`() {
        runTest {
            coEvery { moviesRemoteDataSource.getPopularMovies(page = 1) } returns Result.success(
                mockk<GenericMovieResponse> {
                    every { results } returns listOf(mockk<MovieResponse>(relaxed = true) {
                        every { id } returns 1
                        every { title } returns "Title"
                        every { posterPath } returns "posterPath"
                        every { overview } returns "overview"
                        every { releaseDate } returns "releaseDate"
                        every { voteAverage } returns 1.0
                    })
                }
            )

            val result = moviesRepositoryImpl.getPopularMovies(page = 1)
            assert(result.isSuccess)
            assert(result.getOrNull() is List<Movie>)
            assert(result.getOrNull()?.get(0)?.title == "Title")
            assert(result.getOrNull()?.get(0)?.posterPath == "https://image.tmdb.org/t/p/w500posterPath")
            assert(result.getOrNull()?.get(0)?.overview == "overview")
            assert(result.getOrNull()?.get(0)?.releaseDate == "releaseDate")
            assert(result.getOrNull()?.get(0)?.voteAverage == 1.0)
        }
    }

    @Test
    fun `GIVEN a page WHEN getNowPlayingMovies THEN return a response with a list of movies`() {
        runTest {
            coEvery {
                moviesRemoteDataSource.getNowPlayingMovies(
                    page = 1,
                    maximumDate = "2022-05-28"
                )
            } returns Result.success(
                mockk<GenericMovieResponse>(relaxed = true) {
                    every { results } returns listOf(mockk<MovieResponse>(relaxed = true) {
                        every { id } returns 1
                        every { title } returns "Title"
                        every { posterPath } returns "posterPath"
                        every { overview } returns "overview"
                        every { releaseDate } returns "releaseDate"
                        every { voteAverage } returns 1.0
                    })
                }
            )

            val result =
                moviesRepositoryImpl.getNowPlayingMovies(page = 1, maximumDate = "2022-05-28")
            assert(result.isSuccess)
            assert(result.getOrNull() is List<Movie>)
            assert(result.getOrNull()?.get(0)?.title == "Title")
            assert(result.getOrNull()?.get(0)?.posterPath == "https://image.tmdb.org/t/p/w500posterPath")
            assert(result.getOrNull()?.get(0)?.overview == "overview")
            assert(result.getOrNull()?.get(0)?.releaseDate == "releaseDate")
            assert(result.getOrNull()?.get(0)?.voteAverage == 1.0)

        }
    }

    @Test
    fun `GIVEN a movieId WHEN getMovieDetail THEN return a response with a movie`() {
        runTest {
            coEvery { moviesRemoteDataSource.getMovieDetail(movieId = "1") } returns Result.success(
                mockk<MovieResponse>(relaxed = true) {
                    every { id } returns 1
                    every { title } returns "Title"
                    every { posterPath } returns "posterPath"
                    every { overview } returns "overview"
                    every { releaseDate } returns "releaseDate"
                    every { voteAverage } returns 1.0
                }
            )

            val result = moviesRepositoryImpl.getMovieDetail(movieId = "1")
            assert(result.isSuccess)
            assert(result.getOrNull() is Movie)
            assert(result.getOrNull()?.title == "Title")
            assert(result.getOrNull()?.posterPath == "https://image.tmdb.org/t/p/w500posterPath")
            assert(result.getOrNull()?.overview == "overview")
            assert(result.getOrNull()?.releaseDate == "releaseDate")
            assert(result.getOrNull()?.voteAverage == 1.0)
        }
    }

    @Test
    fun `GIVEN a page WHEN getPopularMovies THEN return an error`() {
        runTest {
            coEvery { moviesRemoteDataSource.getPopularMovies(page = 1) } returns Result.failure(
                Exception()
            )
            val result = moviesRepositoryImpl.getPopularMovies(page = 1)
            assert(result.isFailure)
        }
    }

    @Test
    fun `GIVEN a page WHEN getNowPlayingMovies THEN return an error`() {
        runTest {
            coEvery {
                moviesRemoteDataSource.getNowPlayingMovies(
                    page = 1,
                    maximumDate = "2022-05-28"
                )
            } returns Result.failure(
                Exception()
            )
            val result = moviesRepositoryImpl.getNowPlayingMovies(page = 1, maximumDate = "2022-05-28")
            assert(result.isFailure)
        }
    }

    @Test
    fun `GIVEN a movieId WHEN getMovieDetail THEN return an error`() {
        runTest {
            coEvery { moviesRemoteDataSource.getMovieDetail(movieId = "1") } returns Result.failure(
                Exception()
            )
            val result = moviesRepositoryImpl.getMovieDetail(movieId = "1")
            assert(result.isFailure)
        }
    }
}