package com.technicaltest.feature_movies.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class GenericMovieResponse(
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long
)
