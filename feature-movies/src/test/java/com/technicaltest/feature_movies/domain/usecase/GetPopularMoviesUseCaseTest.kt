package com.technicaltest.feature_movies.domain.usecase

import com.technicaltest.feature_movies.domain.MoviesRepository
import com.technicaltest.feature_movies.domain.entity.Movie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetPopularMoviesUseCaseTest {
    @MockK
    private lateinit var moviesRepository: MoviesRepository


    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        getPopularMoviesUseCase = GetPopularMoviesUseCase(moviesRepository = moviesRepository)
    }

    @Test
    fun `GIVEN a page WHEN getPopularMovies THEN return a response with a list of movies`() {
        runTest {
            coEvery { moviesRepository.getPopularMovies(page = 1) } returns Result.success(
                listOf(mockk(relaxed = true) {
                    every { movieId } returns 1
                    every { title } returns "Title"
                    every { posterPath } returns "posterPath"
                    every { overview } returns "overview"
                    every { releaseDate } returns "releaseDate"
                    every { voteAverage } returns 1.0
                })
            )

            val result = getPopularMoviesUseCase.invoke(page = 1)
            assert(result.isSuccess)
            assert(result.getOrNull() is List<Movie>)
            assert(result.getOrNull()?.get(0)?.title == "Title")
            assert(result.getOrNull()?.get(0)?.posterPath == "posterPath")
            assert(result.getOrNull()?.get(0)?.overview == "overview")
            assert(result.getOrNull()?.get(0)?.releaseDate == "releaseDate")
            assert(result.getOrNull()?.get(0)?.voteAverage == 1.0)
        }
    }

    @Test
    fun `GIVEN a page WHEN getPopularMovies THEN return an error`() {
        runTest {
            coEvery { moviesRepository.getPopularMovies(page = 1) } returns Result.failure(
                Exception()
            )

            val result = getPopularMoviesUseCase.invoke(page = 1)
            assert(result.isFailure)
        }
    }
}
