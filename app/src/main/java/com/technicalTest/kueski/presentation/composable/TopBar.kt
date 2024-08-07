package com.technicalTest.kueski.presentation.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.technicalTest.kueski.R
import com.technicaltest.design_system.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    isGridView: Boolean,
    onLayoutTypeClicked: (Boolean) -> Unit
) {
    val isGridViewState = remember { mutableStateOf(isGridView) }

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = AppTypography.titleMedium
            )
        },
        navigationIcon = {
            IconButton({ navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "menu items"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                isGridViewState.value = !isGridViewState.value
                onLayoutTypeClicked(isGridViewState.value)
            }) {
                Icon(
                    painter = painterResource(id = if (isGridView) com.technicaltest.design_system.R.drawable.list_view else com.technicaltest.design_system.R.drawable.grid_view),
                    contentDescription = null
                )
            }
        }
    )
}