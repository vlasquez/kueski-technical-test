package com.technicalTest.kueski.presentation.composable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.technicaltest.design_system.theme.navigation.NavigationItem
import com.technicaltest.feature_movies.presentation.composable.MovieScreen

@Composable
fun Navigation(navController: NavHostController, isGridView: Boolean) {
    NavHost(navController, startDestination = NavigationItem.PopularMovies.route) {
        composable(NavigationItem.PopularMovies.route) {
            MovieScreen(
                isGridView = isGridView,
                navigationItem = NavigationItem.PopularMovies
            )
        }
        composable(NavigationItem.PlayingNowMovies.route) {
            MovieScreen(
                isGridView = isGridView,
                navigationItem = NavigationItem.PlayingNowMovies
            )
        }
        composable(NavigationItem.FavoriteMovies.route) {
            MovieScreen(
                isGridView = isGridView,
                navigationItem = NavigationItem.FavoriteMovies
            )
        }
    }
}