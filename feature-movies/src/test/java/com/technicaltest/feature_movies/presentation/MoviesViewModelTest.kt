package com.technicaltest.feature_movies.presentation

import app.cash.turbine.turbineScope
import com.technicaltest.feature_movies.MainDispatcherRule
import com.technicaltest.feature_movies.domain.usecase.GetPlayingNowMoviesUseCase
import com.technicaltest.feature_movies.domain.usecase.GetPopularMoviesUseCase
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

class MoviesViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @MockK
    private lateinit var getPlayingNowMoviesUseCase: GetPlayingNowMoviesUseCase

    private lateinit var moviesViewModel: MoviesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        moviesViewModel = MoviesViewModel(getPopularMoviesUseCase, getPlayingNowMoviesUseCase)
    }

    @Test
    fun `GIVEN a page WHEN getPopularMovies THEN return a list of movies`() {
        runTest {
            turbineScope {
                coEvery { getPopularMoviesUseCase(page = 1) } returns Result.success(
                    listOf(
                        mockk(relaxed = true) {
                            every { originalTitle } returns "Test"
                        }
                    )
                )

                val viewState = moviesViewModel.viewState.testIn(backgroundScope)
                assert(viewState.awaitItem() is MoviesViewModel.ViewState.Loading)
                moviesViewModel.getPopularMovies(page = 1)
                val result = viewState.awaitItem()
                assert(result is MoviesViewModel.ViewState.Success)
                assert((result as MoviesViewModel.ViewState.Success).popularMovies?.get(0)?.originalTitle == "Test")
                viewState.cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
fun `GIVEN a page WHEN getPopularMovies THEN return an error message`() {
    runTest {
        turbineScope {
            coEvery { getPopularMoviesUseCase(page = 1) } returns Result.failure(
                Exception()
            )
            val viewState = moviesViewModel.viewState.testIn(backgroundScope)
            assert(viewState.awaitItem() is MoviesViewModel.ViewState.Loading)
            moviesViewModel.getPopularMovies(page = 1)
            val result = viewState.awaitItem()
            assert(result is MoviesViewModel.ViewState.Error)
            viewState.cancelAndIgnoreRemainingEvents()
        }
    }
}

    @Test
    fun `GIVEN a page WHEN getNowPlayingMovies THEN return a list of movies`() {
        runTest {
            turbineScope {
                coEvery {
                    getPlayingNowMoviesUseCase(
                        page = 1,
                        maximumDate = any()
                    )
                } returns Result.success(
                    listOf(
                        mockk(relaxed = true) {
                            every { originalTitle } returns "Test"
                        }
                    )
                )

                val viewState = moviesViewModel.viewState.testIn(backgroundScope)
                assert(viewState.awaitItem() is MoviesViewModel.ViewState.Loading)
                moviesViewModel.getPlayingNowMovies(page = 1)
                val result = viewState.awaitItem()
                assert(result is MoviesViewModel.ViewState.Success)
                assert((result as MoviesViewModel.ViewState.Success).playingNowMovies?.get(0)?.originalTitle == "Test")
                viewState.cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `GIVEN a page WHEN getNowPlayingMovies THEN return an error message`() {
        runTest {
           turbineScope {
                coEvery {
                    getPlayingNowMoviesUseCase(
                        page = 1,
                        maximumDate = any()
                    )
                } returns Result.failure(
                    Exception()
                )
                val viewState = moviesViewModel.viewState.testIn(backgroundScope)
                assert(viewState.awaitItem() is MoviesViewModel.ViewState.Loading)
                moviesViewModel.getPlayingNowMovies(page = 1)
                val result = viewState.awaitItem()
                assert(result is MoviesViewModel.ViewState.Error)
                viewState.cancelAndIgnoreRemainingEvents()
            }
        }
    }
}