package com.technicaltest.feature_movies.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.technicaltest.design_system.R
import com.technicaltest.feature_movies.domain.entity.Movie


@Composable
fun MovieDetailsScreen(movie: Movie) {

    MovieDetailsContent(
        movieImageBackdrop = movie.backdropPath,
        movieImagePoster = movie.posterPath,
        movieTitle = movie.title,
        movieReleaseDate = movie.releaseDate,
        movieOverview = movie.overview,
        movieVoteAverage = movie.voteAverage.toString(),
        movieGenres = emptyList()
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MovieDetailsContent(
    movieImageBackdrop: String?,
    movieImagePoster: String?,
    movieTitle: String,
    movieReleaseDate: String,
    movieOverview: String,
    movieVoteAverage: String,
    movieGenres: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {


        Box {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                ),
            ) {
                Box {
                    GlideImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp),
                        model = movieImageBackdrop,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                    )

                    Card(
                        modifier = Modifier
                            .offset(x = 310.dp, y = 178.dp)
                            .width(54.dp)
                            .height(24.dp)
                            .background(
                                color = Color(0x52252836),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_favorite),
                                contentDescription = null,
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = movieVoteAverage,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(600),
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .offset(x = 29.dp, y = 150.dp)
                    .width(95.dp)
                    .height(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    model = movieImagePoster,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }

            Text(
                modifier = Modifier
                    .width(210.dp)
                    .height(48.dp)
                    .offset(x = 140.dp, y = 220.dp),
                text = movieTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
            )
        }

        Spacer(modifier = Modifier.height(75.dp))


        HorizontalThreeOptions(
            yearRelease = movieReleaseDate,
            duration = "",
            genre = movieGenres.firstOrNull() ?: ""
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.movie_description),
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = movieOverview,
            textAlign = TextAlign.Justify,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
        )

        Spacer(modifier = Modifier.height(24.dp))

        val listGenres = movieGenres.joinToString(separator = " * ") { it }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = listGenres,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(600),
        )
    }
}

@Composable
private fun HorizontalThreeOptions(
    yearRelease: String,
    duration: String,
    genre: String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = yearRelease,
            fontSize = 14.sp,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = duration,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_ticket),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Genre
        Text(
            text = genre,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )

    }

}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(
        moviePreviewProvider().first()
    )
}