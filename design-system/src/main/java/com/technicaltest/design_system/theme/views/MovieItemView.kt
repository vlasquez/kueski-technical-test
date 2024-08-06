package com.technicaltest.design_system.theme.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.technicaltest.design_system.theme.AppTheme
import com.technicaltest.design_system.theme.AppTypography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItemView(
    posterUrl: String?,
    movieTitle: String,
    moviePopularity: Double,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(300.dp)
            .padding(8.dp)
            .clickable {
                onItemClick()
            },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GlideImage(
                model = posterUrl,
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    )
                    .size(200.dp)
                    .fillMaxWidth()
            ) {
                it.centerCrop()
            }
                Text(modifier = Modifier.padding(horizontal = 8.dp),
                    text = movieTitle,
                    style = AppTypography.bodySmall
                )
        }
    }
}

@Composable
@Preview
private fun MoviePreview() {
    AppTheme {
        MovieItemView(
            posterUrl = "https://image.tmdb.org/t/p/w500/z1p34vh7dEOnLDmyCrlUVLuoDzd.jpg",
            movieTitle = "The Godfather",
            moviePopularity = 3.0,
            onItemClick = {}
        )
    }
}
