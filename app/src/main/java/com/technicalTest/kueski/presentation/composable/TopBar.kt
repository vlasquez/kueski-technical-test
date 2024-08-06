package com.technicalTest.kueski.presentation.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.technicalTest.kueski.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(isGridView: Boolean, onLayoutTypeClicked: (Boolean) -> Unit) {
    val isGridViewState = remember { mutableStateOf(isGridView) }

    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        actions = {
            IconButton(onClick = {
                isGridViewState.value = !isGridViewState.value
                onLayoutTypeClicked(isGridViewState.value)
            }) {
                Icon(
                    imageVector = if (isGridView) Icons.Default.MailOutline else Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    )
}