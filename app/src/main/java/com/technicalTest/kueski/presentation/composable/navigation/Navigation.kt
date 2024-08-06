package com.technicalTest.kueski.presentation.composable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.technicaltest.feature_movies.presentation.composable.MovieScreen

@Composable
fun Navigation(navController: NavHostController, isGridView: Boolean) {
    NavHost(navController, startDestination = NavigationItem.PopularMovies.route) {
        composable(NavigationItem.PopularMovies.route) {
            MovieScreen(modifier = Modifier, isGridView = isGridView)
        }
        composable(NavigationItem.PlayingNowMovies.route) {
            MovieScreen(modifier = Modifier, isGridView = isGridView)
        }
        composable(NavigationItem.FavoriteMovies.route) {
            //MoviesScreen()
        }
    }
}