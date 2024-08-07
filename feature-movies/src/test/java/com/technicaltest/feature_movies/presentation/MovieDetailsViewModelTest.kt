package com.technicaltest.feature_movies.presentation

import app.cash.turbine.turbineScope
import com.technicaltest.feature_movies.MainDispatcherRule
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.domain.usecase.GetMovieDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        movieDetailsViewModel = MovieDetailsViewModel(getMovieDetailUseCase)
    }

    @Test
    fun `GIVEN a movie id WHEN getMovieDetail THEN get the movie details`() {
        runTest {
            turbineScope {
                coEvery { getMovieDetailUseCase(movieId = any()) } returns Result.success(
                    mockk<Movie>(
                        relaxed = true
                    ) {
                        every { originalTitle } returns "Test"
                    })

                val viewState = movieDetailsViewModel.viewState.testIn(backgroundScope)
                assert(viewState.awaitItem() is MovieDetailsViewModel.ViewState.Loading)
                movieDetailsViewModel.getMovieDetail(movieId = "123")
                val result = viewState.awaitItem()
                assert(result is MovieDetailsViewModel.ViewState.Success)
                assert((result as MovieDetailsViewModel.ViewState.Success).movie.originalTitle == "Test")
            }
        }
    }

    @Test
    fun `GIVEN a movie id WHEN getMovieDetail THEN get an error message`() {
        runTest {
            turbineScope {

                coEvery { getMovieDetailUseCase(movieId = any()) } returns Result.failure(
                    Exception("Error")
                )
                val viewState = movieDetailsViewModel.viewState.testIn(backgroundScope)
                assert(viewState.awaitItem() is MovieDetailsViewModel.ViewState.Loading)
                movieDetailsViewModel.getMovieDetail(movieId = "123")
                val result = viewState.awaitItem()
                assert(result is MovieDetailsViewModel.ViewState.Error)
                assert((result as MovieDetailsViewModel.ViewState.Error).message == "Error")
            }
        }
    }
}