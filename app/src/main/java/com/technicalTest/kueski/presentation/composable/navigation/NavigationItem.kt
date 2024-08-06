package com.technicalTest.kueski.presentation.composable.navigation

import com.technicaltest.design_system.R


sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    data object PopularMovies :
        NavigationItem("popularMovies", R.drawable.popular_movies, "Popular")

    data object PlayingNowMovies :
        NavigationItem("playingNowMovies", R.drawable.playing_now_movies, "Playing Now")

    data object FavoriteMovies :
        NavigationItem("favoriteMovies", R.drawable.favorite_movies, "Favorites")

}