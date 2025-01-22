@file:OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)

package id.haaweejee.test.alfagift.alfamovie.presentation.pages.detail.screen

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import id.haaweejee.test.alfagift.alfamovie.R
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieVideo
import id.haaweejee.test.alfagift.alfamovie.presentation.common.ViewState
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.ErrorAnimation
import id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.detail.detailContentOrganismComponent
import id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.detail.detailHeaderOrganismComponent
import id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.detail.detailReviewsOrganismComponent
import id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.detail.detailYoutubeVideoPlayerOrganismComponent
import id.haaweejee.test.alfagift.alfamovie.presentation.pages.detail.vm.DetailMovieViewModel
import id.haaweejee.test.alfagift.alfamovie.presentation.util.NetworkStateTracker
import id.haaweejee.test.alfagift.alfamovie.presentation.util.NetworkStatus
import id.haaweejee.test.alfagift.alfamovie.presentation.util.getActivity

@Composable
fun DetailScreen(
    viewModel: DetailMovieViewModel
) {
    val lazyListState = rememberLazyListState()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val networkStateTracker = remember { NetworkStateTracker(context) }
    val networkStatus by networkStateTracker.observeNetworkState()
        .collectAsState(initial = NetworkStatus.Unavailable)
    val isConnected = networkStatus is NetworkStatus.Available
    var isLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(isConnected) {
        if (state.detailMovie !is ViewState.ViewSuccess) {
            viewModel.getDetailMovie()
            viewModel.getMovieReviews()
            viewModel.getMovieVideo()
            isLoaded = true
        }
    }

    Scaffold(
        modifier = Modifier,
        containerColor = Color(0xFF222222),
    ) { innerPadding ->
        if (!isConnected && isLoaded) {
            ErrorAnimation(
                lottie = R.raw.no_internet,
                title = "No Internet",
                message = "Please Check your Internet Connection",
                actionText = "Go to Settings",
                navigateText = "Back to Homepage",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                onAction = {
                    val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                    context.startActivity(intent)
                },
                onNavigate = {
                    context.getActivity()?.finish()
                }
            )
        } else {
            when (state.detailMovie) {
                ViewState.ViewEmpty -> {
                }

                is ViewState.ViewError -> {
                    val title = (state.detailMovie as? ViewState.ViewError)?.title.orEmpty()
                    val message =
                        (state.detailMovie as? ViewState.ViewError)?.message.orEmpty()
                    ErrorAnimation(
                        lottie = R.raw.error_animation,
                        title = title,
                        message = message,
                        actionText = "Retry",
                        navigateText = "Back to Homepage",
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 16.dp),
                        onAction = {
                            viewModel.getDetailMovie()
                            viewModel.getMovieVideo()
                            viewModel.getMovieReviews()
                        },
                        onNavigate = {
                            context.getActivity()?.finish()
                        }
                    )
                }

                is ViewState.ViewFailed -> {
                    val message =
                        (state.detailMovie as? ViewState.ViewFailed)?.exception?.message.orEmpty()
                    ErrorAnimation(
                        lottie = R.raw.error_animation,
                        title = "Something when Wrong",
                        message = message,
                        actionText = "Retry",
                        navigateText = "Back to Homepage",
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 16.dp),
                        onAction = {
                            viewModel.getDetailMovie()
                            viewModel.getMovieVideo()
                            viewModel.getMovieReviews()
                        },
                        onNavigate = {
                            context.getActivity()?.finish()
                        }
                    )
                }

                ViewState.ViewLoading -> Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = Color(0xFF444444)
                    )
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
                        if (data != null) {
                            detailHeaderOrganismComponent(
                                data = data,
                                onFinish = { context.getActivity()?.finish() }
                            )
                            detailContentOrganismComponent(data = data)
                            detailYoutubeVideoPlayerOrganismComponent(videoKey)
                            detailReviewsOrganismComponent(data = movieReview)
                        }
                    }
                }
            }
        }
    }
}
