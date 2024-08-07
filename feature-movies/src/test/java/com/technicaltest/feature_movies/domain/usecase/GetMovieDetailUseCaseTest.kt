package com.technicaltest.feature_movies.domain.usecase

import com.technicaltest.feature_movies.domain.MoviesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetMovieDetailUseCaseTest {

    @MockK
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        getMovieDetailUseCase = GetMovieDetailUseCase(moviesRepository = moviesRepository)
    }

    @Test
    fun `GIVEN a movieId WHEN getMovieDetail THEN return a movie`() {
        runTest {
            coEvery { moviesRepository.getMovieDetail(movieId = "1") } returns Result.success(
                mockk(relaxed = true) {
                    every { movieId } returns 1
                    every { title } returns "Title"
                    every { posterPath } returns "posterPath"
                    every { overview } returns "overview"
                    every { releaseDate } returns "releaseDate"
                    every { voteAverage } returns 1.0
                }
            )

            val result = getMovieDetailUseCase.invoke(movieId = "1")
            assert(result.isSuccess)
            assert(result.getOrNull()?.movieId == 1L)
            assert(result.getOrNull()?.title == "Title")
            assert(result.getOrNull()?.posterPath == "posterPath")
            assert(result.getOrNull()?.overview == "overview")
            assert(result.getOrNull()?.releaseDate == "releaseDate")
            assert(result.getOrNull()?.voteAverage == 1.0)
        }
    }

    @Test
    fun `GIVEN a movieId WHEN getMovieDetail THEN return an error`() {
        runTest {
            coEvery { moviesRepository.getMovieDetail(movieId = "1") } returns Result.failure(
                Exception()
            )

            val result = getMovieDetailUseCase.invoke(movieId = "1")
            assert(result.isFailure)
        }
    }
}