package com.technicaltest.feature_movies.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Long,
    val title: String,
    val originalTitle: String,
    val posterPath: String,
    val releaseDate: String,
    val overview: String,
    val popularity: Double,
    val voteCount: Long,
    val voteAverage: Double,
    val genreIds: List<Long>,
    val isFavorite: Boolean
) : Parcelable
