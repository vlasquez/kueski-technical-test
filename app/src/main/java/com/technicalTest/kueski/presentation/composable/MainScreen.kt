package com.technicalTest.kueski.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.technicalTest.kueski.presentation.composable.navigation.Navigation

@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    var isGridView by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopBar(isGridView = isGridView) { isGridViewState ->
                isGridView = isGridViewState
            }
        },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController, isGridView = isGridView)
            }
        },
    )
}