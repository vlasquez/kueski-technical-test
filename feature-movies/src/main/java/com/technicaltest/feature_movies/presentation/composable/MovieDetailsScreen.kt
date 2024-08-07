package com.technicaltest.feature_movies.presentation.composable

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.technicaltest.design_system.R
import com.technicaltest.design_system.theme.AppTypography
import com.technicaltest.design_system.theme.Layout
import com.technicaltest.design_system.theme.Padding
import com.technicaltest.design_system.theme.views.LoadingAnimation
import com.technicaltest.feature_movies.domain.entity.Genre
import com.technicaltest.feature_movies.domain.entity.Movie
import com.technicaltest.feature_movies.presentation.MovieDetailsViewModel


@Composable
fun MovieDetailsScreen(movie: Movie) {
    val viewModel: MovieDetailsViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsState()

    val movieDetail = remember { mutableStateOf<Movie?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getMovieDetail(movieId = movie.movieId.toString())
    }

    when (viewState) {
        is MovieDetailsViewModel.ViewState.Loading -> {
            LoadingAnimation()
        }

        is MovieDetailsViewModel.ViewState.Success -> {
            movieDetail.value = (viewState as MovieDetailsViewModel.ViewState.Success).movie
        }

        is MovieDetailsViewModel.ViewState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (viewState as MovieDetailsViewModel.ViewState.Error).message
                    ?: stringResource(id = R.string.generic_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    MovieDetailsContent(
        movieImageBackdrop = movie.backdropPath,
        movieImagePoster = movie.posterPath,
        movieTitle = movie.title,
        movieReleaseDate = movie.releaseDate,
        movieOverview = movie.overview,
        movieVoteAverage = movie.voteAverage.toString(),
        movieRuntime = movieDetail.value?.runtime,
        movieGenres = movieDetail.value?.genres ?: emptyList()
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
    movieRuntime: Int?,
    movieGenres: List<Genre>?
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
                    .height(MOVIE_BACKDROP_HEIGHT.dp),
                shape = RoundedCornerShape(
                    bottomEnd = Layout.Spacing.Medium.S,
                    bottomStart = Layout.Spacing.Medium.S
                ),
            ) {
                Box {
                    GlideImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(MOVIE_BACKDROP_HEIGHT.dp),
                        model = movieImageBackdrop,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                    )

                    Card(
                        modifier = Modifier
                            .offset(x = 310.dp, y = 178.dp)
                            .width(54.dp)
                            .height(Layout.Spacing.Medium.S)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(size = Padding.Small.S)
                            )
                            .padding(
                                start = Padding.Small.S,
                                top = Padding.Small.Xs,
                                end = Padding.Small.S,
                                bottom = Padding.Small.Xs
                            ),
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
                                style = AppTypography.bodySmall,
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .offset(x = 29.dp, y = 150.dp)
                    .width(95.dp)
                    .height(Layout.Spacing.Large.L),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                ),
                shape = RoundedCornerShape(Layout.Spacing.Small.L),
            ) {
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MOVIE_BACKDROP_HEIGHT.dp),
                    model = movieImagePoster,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }

            Text(
                modifier = Modifier
                    .width(MOVIE_BACKDROP_HEIGHT.dp)
                    .height(48.dp)
                    .offset(x = 140.dp, y = 220.dp),
                text = movieTitle,
                style = AppTypography.bodyLarge,
            )
        }

        Spacer(modifier = Modifier.height(Layout.Spacing.Large.M))


        HorizontalThreeOptions(
            yearRelease = movieReleaseDate,
            duration = movieRuntime,
            genre = movieGenres?.firstOrNull()?.name ?: ""
        )

        Spacer(modifier = Modifier.height(Layout.Spacing.Medium.S))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.Medium.S),
            text = stringResource(R.string.movie_description),
            style = AppTypography.headlineSmall,
        )

        Spacer(modifier = Modifier.height(Layout.Spacing.Small.M))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.Medium.S),
            text = movieOverview,
            textAlign = TextAlign.Justify,
            style = AppTypography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(Padding.Medium.S))

        val genreList = movieGenres?.joinToString(separator = " * ") { it.name }

        genreList?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Padding.Medium.S),
                text = it,
                textAlign = TextAlign.Center,
                style = AppTypography.bodyLarge,
            )
        }
    }
}

@Composable
private fun HorizontalThreeOptions(
    yearRelease: String,
    duration: Int?,
    genre: String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Padding.Medium.S),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier.size(Layout.Spacing.Small.L),
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(Layout.Spacing.Small.Xs))

        Text(
            text = yearRelease,
            style = AppTypography.bodyMedium,
        )

        Spacer(modifier = Modifier.width(Layout.Spacing.Small.M))

        duration?.let {
            Icon(
                painter = painterResource(id = R.drawable.ic_vertical_line),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(Layout.Spacing.Small.M))

            Icon(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(Layout.Spacing.Small.Xs))
            Text(
                text = stringResource(
                    R.string.movie_duration,
                    it.toString()
                ),
                style = AppTypography.bodyMedium,
                fontWeight = FontWeight(600),
            )
        }

        Spacer(modifier = Modifier.width(Layout.Spacing.Small.M))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(Layout.Spacing.Small.M))

        Icon(
            painter = painterResource(id = R.drawable.ic_ticket),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(Layout.Spacing.Small.Xs))
        Text(
            text = genre,
            style = AppTypography.bodyMedium
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

const val MOVIE_BACKDROP_HEIGHT = 210