package com.technicalTest.kueski.presentation.composable

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.technicaltest.design_system.theme.AppTheme
import com.technicaltest.design_system.theme.navigation.NavigationItem


@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        NavigationItem.PopularMovies,
        NavigationItem.PlayingNowMovies,
        NavigationItem.FavoriteMovies
    )

    NavigationBar(
        contentColor = Color.White
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                    )
                },
                label = { Text(text = stringResource(id = item.title)) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    AppTheme {
        BottomNavigationBar(navController = rememberNavController())
    }
}
