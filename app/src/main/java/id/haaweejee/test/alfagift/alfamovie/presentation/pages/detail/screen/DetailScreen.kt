@file:OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)

package id.haaweejee.test.alfagift.alfamovie.presentation.pages.detail.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import id.haaweejee.test.alfagift.alfamovie.R
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo
import id.haaweejee.test.alfagift.alfamovie.presentation.common.ViewState
import id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms.DetailMovieColumnInformation
import id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms.YoutubePlayer
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.ReviewCard
import id.haaweejee.test.alfagift.alfamovie.presentation.pages.detail.vm.DetailMovieViewModel
import id.haaweejee.test.alfagift.alfamovie.presentation.util.detailedDuration
import id.haaweejee.test.alfagift.alfamovie.presentation.util.localeDateDay

@Composable
fun DetailScreen(
    viewModel: DetailMovieViewModel
) {
    val lazyListState = rememberLazyListState()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        if (state.detailMovie !is ViewState.ViewSuccess) {
            viewModel.getDetailMovie()
        }
        if (state.movieReviews !is ViewState.ViewSuccess) {
            viewModel.getMovieReviews()
        }
        if (state.movieVideo !is ViewState.ViewSuccess) {
            viewModel.getMovieVideo()
        }
    }

    Scaffold(
        modifier = Modifier,
        containerColor = Color(0xFF222222),
    ) { innerPadding ->
        when (state.detailMovie) {
            ViewState.ViewEmpty -> {
            }

            is ViewState.ViewError -> {

            }

            is ViewState.ViewFailed -> {

            }

            ViewState.ViewLoading -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }

            is ViewState.ViewSuccess -> {
                val data = (state.detailMovie as? ViewState.ViewSuccess<DetailMovie>)?.data
                val videoKey =
                    (state.movieVideo as? ViewState.ViewSuccess<MovieVideo>)?.data?.key.orEmpty()
                val movieReview =
                    (state.movieReviews as? ViewState.ViewSuccess<List<MovieReview>>)?.data.orEmpty()
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    state = lazyListState
                ) {
                    stickyHeader {
                        Box(modifier = Modifier) {
                            AsyncImage(
                                data?.backdrop.orEmpty(),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp),
                                placeholder = painterResource(
                                    R.drawable.image_loading_placeholder
                                )
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .background(color = Color(0xFF444444).copy(alpha = 0.6f)),
                            )
                            Row(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            AsyncImage(
                                data?.poster,
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(160.dp)
                                    .align(Alignment.BottomCenter)
                                    .clip(RoundedCornerShape(4.dp)),
                                placeholder = painterResource(
                                    R.drawable.image_loading_placeholder
                                )
                            )
                        }

                    }
                    item {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = data?.title.orEmpty(),
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                            Row(
                                modifier = Modifier.padding(top = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                DetailMovieColumnInformation(
                                    title = "Release Date",
                                    value = data?.releaseDate?.localeDateDay()
                                        .orEmpty()
                                )
                                DetailMovieColumnInformation(
                                    title = "Duration",
                                    value = data?.duration?.detailedDuration().orEmpty(),
                                )
                                DetailMovieColumnInformation(
                                    title = "Status",
                                    value = data?.status.orEmpty()
                                )
                            }
                            Text(
                                text = "Genres",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                data?.genres?.forEach {
                                    Box(
                                        modifier = Modifier.background(
                                            color = Color(0xFF444444),
                                            shape = RoundedCornerShape(12.dp),
                                        ),
                                    ) {
                                        Text(
                                            text = it,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 10.sp,
                                            color = Color.White,
                                            modifier = Modifier.padding(12.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = "Overview",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            Text(
                                text = data?.overview.orEmpty(),
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                    item {
                        YoutubePlayer(key = videoKey)
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Reviews",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            movieReview.take(3).forEach {
                                ReviewCard(
                                    review = it
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
