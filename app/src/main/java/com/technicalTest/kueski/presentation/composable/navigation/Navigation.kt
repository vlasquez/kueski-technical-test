package com.technicalTest.kueski.presentation.composable.navigation

import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.technicaltest.design_system.theme.navigation.NavigationItem
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.presentation.composable.MovieDetailsScreen
import com.technicaltest.feature_movies.presentation.composable.MovieScreen

@Composable
fun Navigation(navController: NavHostController, isGridView: Boolean) {
    NavHost(navController, startDestination = NavigationItem.PopularMovies.route) {
        composable(route = NavigationItem.PopularMovies.route) {
            MovieScreen(
                isGridView = isGridView,
                navigationItem = NavigationItem.PopularMovies,
                navController = navController,
            )
        }
        composable(route = NavigationItem.PlayingNowMovies.route) {

            MovieScreen(
                isGridView = isGridView,
                navigationItem = NavigationItem.PlayingNowMovies,
                navController = navController,
            )
        }
        composable(route = NavigationItem.FavoriteMovies.route) {
            MovieScreen(
                isGridView = isGridView,
                navigationItem = NavigationItem.FavoriteMovies,
                navController = navController,
            )
        }
        composable(
            route = "movieDetails/{${MOVIE_PARAM}}",
            arguments = listOf(navArgument(MOVIE_PARAM) { type = MovieNavType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getParcelable<Movie>(MOVIE_PARAM)?.let { movie ->
                MovieDetailsScreen(
                    movie = movie
                )
            }
        }
    }
}

val MovieNavType = object : NavType<Movie>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Movie? =
        bundle.getParcelable(key)

    override fun parseValue(value: String): Movie =
        Gson().fromJson(Uri.decode(value), Movie::class.java)

    override fun put(bundle: Bundle, key: String, value: Movie) {
        bundle.putParcelable(key, value)
    }
}

const val MOVIE_PARAM = "movie"