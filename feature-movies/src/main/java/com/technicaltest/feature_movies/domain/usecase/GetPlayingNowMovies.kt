package com.technicaltest.feature_movies.domain.usecase

import com.technicaltest.feature_movies.domain.MoviesRepository
import javax.inject.Inject

class GetPlayingNowMovies @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(
        page: Int,
        minimumDate: String,
        maximumDate: String
    ) = moviesRepository.getNowPlayingMovies(
        page = page,
        minimumDate = minimumDate,
        maximumDate = maximumDate
    )
}