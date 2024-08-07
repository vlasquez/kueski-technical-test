package com.technicaltest.feature_movies.domain.usecase

import com.technicaltest.feature_movies.domain.MoviesRepository
import com.technicaltest.feature_movies.domain.entity.Movie
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movieId: String): Result<Movie> =
        moviesRepository.getMovieDetail(movieId = movieId)
}