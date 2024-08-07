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

class GetPlayingNowMoviesUseCaseTest {

    @MockK
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getPlayingNowMoviesUseCase: GetPlayingNowMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        getPlayingNowMoviesUseCase = GetPlayingNowMoviesUseCase(moviesRepository = moviesRepository)
    }

    @Test
    fun `GIVEN a page WHEN getPlayingNowMovies THEN return a response with a list of movies`() {
        runTest {
            coEvery {
                moviesRepository.getNowPlayingMovies(
                    page = 1,
                    maximumDate = "2022-05-28"
                )
            } returns Result.success(
                listOf(mockk<Movie>(relaxed = true) {
                    every { movieId } returns 1
                    every { title } returns "Title"
                    every { posterPath } returns "posterPath"
                    every { overview } returns "overview"
                    every { releaseDate } returns "releaseDate"
                    every { voteAverage } returns 1.0
                })
            )

            val result = getPlayingNowMoviesUseCase.invoke(page = 1, maximumDate = "2022-05-28")
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
    fun `GIVEN a page WHEN getPlayingNowMovies THEN return an error`() {
        runTest {
            coEvery {
                moviesRepository.getNowPlayingMovies(
                    page = 1,
                    maximumDate = "2022-05-28"
                )
            } returns Result.failure(Exception())

            val result = getPlayingNowMoviesUseCase.invoke(page = 1, maximumDate = "2022-05-28")
            assert(result.isFailure)
        }
    }
}