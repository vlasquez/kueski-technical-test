package com.technicaltest.feature_movies.di

import com.technicaltest.feature_movies.data.MoviesRepositoryImpl
import com.technicaltest.feature_movies.data.datasource.local.MoviesLocalDataSource
import com.technicaltest.feature_movies.data.datasource.local.MoviesLocalDataSourceImpl
import com.technicaltest.feature_movies.data.datasource.remote.MoviesApi
import com.technicaltest.feature_movies.data.datasource.remote.MoviesRemoteDataSource
import com.technicaltest.feature_movies.data.datasource.remote.MoviesRemoteDataSourceImpl
import com.technicaltest.feature_movies.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {

    @Provides
    @Singleton
    fun provideMoviesRemoteDataSource(
        moviesApi: MoviesApi
    ): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImpl(
            moviesApi = moviesApi
        )
    }

    @Provides
    @Singleton
    fun provideMoviesLocalDataSource(
    ): MoviesLocalDataSource {
        return MoviesLocalDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesLocalDataSource: MoviesLocalDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(
            moviesRemoteDataSource = moviesRemoteDataSource,
            moviesLocalDataSource = moviesLocalDataSource
        )
    }

    companion object {

        @Singleton
        @Provides
        fun provideMoviesApi(retrofit: Retrofit): MoviesApi =
            retrofit.create(MoviesApi::class.java)
    }
}