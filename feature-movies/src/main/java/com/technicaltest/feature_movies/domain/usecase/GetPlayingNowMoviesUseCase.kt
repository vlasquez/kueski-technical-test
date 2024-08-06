package com.technicaltest.feature_movies.domain.usecase

import com.technicaltest.feature_movies.domain.MoviesRepository
import javax.inject.Inject

class GetPlayingNowMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(
        page: Int,
        maximumDate: String
    ) = moviesRepository.getNowPlayingMovies(
        page = page,
        maximumDate = maximumDate
    )
}