package com.technicaltest.design_system.theme.navigation

import androidx.annotation.StringRes
import com.technicaltest.design_system.R


sealed class NavigationItem(var route: String, var icon: Int, @StringRes var title: Int) {
    data object PopularMovies :
        NavigationItem(
            "popularMovies",
            R.drawable.popular_movies,
            R.string.popular_movies_title
        )

    data object PlayingNowMovies :
        NavigationItem(
            "playingNowMovies",
            R.drawable.playing_now_movies,
            R.string.playing_now_movie_title
        )

    data object FavoriteMovies :
        NavigationItem(
            "favoriteMovies",
            R.drawable.favorite_movies,
            R.string.favorites_movies_title
        )

}