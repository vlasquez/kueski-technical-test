package com.technicaltest.feature_movies.data.datasource.remote

import com.technicaltest.feature_movies.data.datasource.remote.model.GenericMovieResponse
import com.technicaltest.network.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MoviesApi {

    @GET("/3/discover/movie")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Result<GenericMovieResponse>

    @GET("/3/discover/movie")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("with_release_type") releaseType: String = "2|3",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("release_date.gte") minimumDate: String,
        @Query("release_date.lte") maximumDate: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Result<GenericMovieResponse>
}