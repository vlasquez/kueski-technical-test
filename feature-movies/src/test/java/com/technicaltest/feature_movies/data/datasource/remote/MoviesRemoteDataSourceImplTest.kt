package com.technicaltest.feature_movies.data.datasource.remote

import com.technicaltest.feature_movies.data.datasource.remote.model.GenericMovieResponse
import com.technicaltest.feature_movies.data.datasource.remote.model.MovieResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MoviesRemoteDataSourceImplTest {

    @MockK
    private lateinit var moviesApi: MoviesApi

    private lateinit var moviesRemoteDataSourceImpl: MoviesRemoteDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        moviesRemoteDataSourceImpl = MoviesRemoteDataSourceImpl(moviesApi)
    }

    @Test
    fun `GIVEN a page WHEN getPopularMovies THEN return a response with a list of movies`() {
        runTest {
            coEvery {
                moviesApi.getPopularMovies(page = 1)
            } returns Result.success(
                mockk<GenericMovieResponse> {
                    every { results } returns listOf(
                        mockk<MovieResponse>(relaxed = true) {
                            every { originalTitle } returns "Test"
                        }
                    )
                }
            )
            val result = moviesRemoteDataSourceImpl.getPopularMovies(page = 1)

            assert(result.isSuccess)
            Assert.assertNotNull(result.getOrNull()?.results)
            Assert.assertEquals("Test", result.getOrNull()?.results?.get(0)?.originalTitle)
        }
    }

    @Test
    fun `GIVEN a page WHEN getNowPlayingMovies THEN return a response with a list of movies`() {
        runTest {
            coEvery {
                moviesApi.getNowPlayingMovies(
                    page = 1,
                    minimumDate = MINIMUM_DATE,
                    maximumDate = "2022-05-28"
                )
            } returns Result.success(
                GenericMovieResponse(
                    totalResults = 1, totalPages = 1, results = listOf()
                )
            )
            val result = moviesRemoteDataSourceImpl.getNowPlayingMovies(
                page = 1,
                maximumDate = "2022-05-28"
            )

            assert(result.isSuccess)
            Assert.assertNotNull(result.getOrNull()?.results)
        }
    }

    @Test
    fun `GIVEN a movieId WHEN getMovieDetail THEN return a response with a movie`() {
        runTest {
            coEvery {
                moviesApi.getMovieDetail(movieId = "123")
            } returns Result.success(
                mockk<MovieResponse> {
                    every { originalTitle } returns "Test"
                    every { id } returns 123
                    every { overview } returns "Test"
                    every { posterPath } returns "Test"
                    every { releaseDate } returns "Test"
                    every { title } returns "Test"
                    every { voteAverage } returns 1.0
                    every{genres} returns listOf(
                        mockk(relaxed = true) {
                            every { name } returns "Test"
                        }
                    )
                }
            )
            val result = moviesRemoteDataSourceImpl.getMovieDetail(movieId = "123")

            assert(result.isSuccess)
            Assert.assertNotNull(result.getOrNull())
            assert(result.getOrNull()?.originalTitle == "Test")
            assert(result.getOrNull()?.id == 123L)
            assert(result.getOrNull()?.overview == "Test")
            assert(result.getOrNull()?.posterPath == "Test")
            assert(result.getOrNull()?.releaseDate == "Test")
            assert(result.getOrNull()?.title == "Test")
            assert(result.getOrNull()?.voteAverage == 1.0)
            assert(result.getOrNull()?.genres?.get(0)?.name == "Test")
        }
    }

    @Test
    fun `GIVEN a page When getPopularMovies THEN return an error`() {
        runTest {
            coEvery {
                moviesApi.getPopularMovies(page = 1)
            } returns Result.failure(
                Exception()
            )
            val result = moviesRemoteDataSourceImpl.getPopularMovies(page = 1)

            assert(result.isFailure)
        }
    }

    @Test
    fun `GIVEN a page When getNowPlayingMovies THEN return an error`() {
        runTest {
            coEvery {
                moviesApi.getNowPlayingMovies(
                    page = 1,
                    minimumDate = MINIMUM_DATE,
                    maximumDate = "2022-05-28"
                )
            } returns Result.failure(
                Exception()
            )
            val result = moviesRemoteDataSourceImpl.getNowPlayingMovies(
                page = 1,
                maximumDate = "2022-05-28"
            )

            assert(result.isFailure)
        }
    }

    @Test
    fun `GIVEN a movieId When getMovieDetail THEN return an error`() {
        runTest {
            coEvery {
                moviesApi.getMovieDetail(movieId = "123")
            } returns Result.failure(
                Exception()
            )
            val result = moviesRemoteDataSourceImpl.getMovieDetail(movieId = "123")

            assert(result.isFailure)
        }
    }
}