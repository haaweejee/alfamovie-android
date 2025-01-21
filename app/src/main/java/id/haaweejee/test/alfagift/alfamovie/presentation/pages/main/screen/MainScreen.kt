package id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.screen

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import id.haaweejee.test.alfagift.alfamovie.R
import id.haaweejee.test.alfagift.alfamovie.presentation.common.ViewState
import id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms.GridViewButton
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.ErrorAnimation
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieGridCard
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieGridCardShimmer
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieListCard
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieListCardShimmer
import id.haaweejee.test.alfagift.alfamovie.presentation.pages.detail.DetailMovieActivity
import id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.vm.MainViewModel
import id.haaweejee.test.alfagift.alfamovie.presentation.util.NetworkStateTracker
import id.haaweejee.test.alfagift.alfamovie.presentation.util.NetworkStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val gridState = rememberLazyGridState()
    var isGridViewActive by remember { mutableStateOf(false) }
    val networkStateTracker = remember { NetworkStateTracker(context) }
    val networkStatus by networkStateTracker.observeNetworkState()
        .collectAsState(initial = NetworkStatus.Unavailable)
    val isConnected = networkStatus is NetworkStatus.Available
    var isLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(isConnected) {
        if (state.discoverMovie !is ViewState.ViewSuccess) {
            viewModel.getDiscoverMovies()
            isLoaded = true
        }
    }

    // Check if the last item is visible
    LaunchedEffect(listState) {
        snapshotFlow {
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleItemIndex
        }.collect { isLastItemVisible ->
            val dataLoaded = (state.discoverMovie as? ViewState.ViewSuccess)?.data ?: emptyList()
            if (
                isLastItemVisible == dataLoaded.size.minus(
                    1
                ) && (state.discoverMovie is ViewState.ViewSuccess)
            ) {
                viewModel.setDiscoverMoviePage(page = state.page + 1)
                viewModel.getNextPageDiscoverMovies()
            }
        }
    }

    // Check if the last item is visible
    LaunchedEffect(gridState) {
        snapshotFlow {
            val lastVisibleItemIndex = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleItemIndex
        }.collect { isLastItemVisible ->
            val dataLoaded = (state.discoverMovie as? ViewState.ViewSuccess)?.data ?: emptyList()
            if (
                isLastItemVisible == dataLoaded.size.minus(
                    1
                ) && (state.discoverMovie is ViewState.ViewSuccess)
            ) {
                viewModel.setDiscoverMoviePage(page = state.page + 1)
                viewModel.getNextPageDiscoverMovies()
            }
        }
    }


    Scaffold(
        modifier = Modifier,
        containerColor = Color(0xFF222222),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF444444)
                ),
                title = {
                    Text(
                        text = "Alfa Movie",
                        color = Color.White
                    )
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(
                                    color = if (isConnected) Color.Green else Color.Red,
                                    shape = CircleShape
                                ),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        GridViewButton(isGridView = isGridViewActive) {
                            isGridViewActive = !isGridViewActive
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (!isConnected && isLoaded) {
                ErrorAnimation(
                    lottie = R.raw.no_internet,
                    title = "No Internet",
                    message = "Please Check your Internet Connection",
                    actionText = "Go to Settings",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp),
                    onAction = {
                        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                        context.startActivity(intent)
                    }
                )
            } else {
                when (state.discoverMovie) {
                    ViewState.ViewLoading -> {
                        if (isGridViewActive) {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                contentPadding = PaddingValues(16.dp)
                            ) {
                                items(20) {
                                    MovieGridCardShimmer()
                                }
                            }
                        } else {
                            Column(
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .padding(horizontal = 16.dp)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                repeat(10) {
                                    MovieListCardShimmer()
                                }
                            }
                        }
                    }

                    ViewState.ViewEmpty -> {}
                    is ViewState.ViewError -> {
                        val title = (state.discoverMovie as? ViewState.ViewError)?.title.orEmpty()
                        val message =
                            (state.discoverMovie as? ViewState.ViewError)?.message.orEmpty()
                        ErrorAnimation(
                            lottie = R.raw.error_animation,
                            title = title,
                            message = message,
                            actionText = "Retry",
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .padding(horizontal = 16.dp),
                            onAction = { viewModel.getDiscoverMovies() }
                        ) {
                            viewModel.getDiscoverMovies()
                        }
                    }

                    is ViewState.ViewFailed -> {
                        val message =
                            (state.discoverMovie as? ViewState.ViewFailed)?.exception?.message.orEmpty()
                        ErrorAnimation(
                            lottie = R.raw.error_animation,
                            title = "Something when Wrong",
                            message = message,
                            actionText = "Retry",
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .padding(horizontal = 16.dp)
                        ) {
                            viewModel.getDiscoverMovies()
                        }
                    }

                    is ViewState.ViewSuccess -> {
                        if (isGridViewActive) {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2), // Number of columns
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(16.dp),
                                state = gridState,
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(
                                    (state.discoverMovie as? ViewState.ViewSuccess)?.data
                                        ?: emptyList()
                                ) {
                                    MovieGridCard(
                                        modifier = Modifier,
                                        data = it
                                    ) {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                DetailMovieActivity::class.java
                                            ).putExtra(
                                                DetailMovieActivity.EXTRA_MOVIE_ID, it.id
                                            )
                                        )
                                    }
                                }

                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(16.dp),
                                state = listState,
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(
                                    (state.discoverMovie as? ViewState.ViewSuccess)?.data
                                        ?: emptyList()
                                ) {
                                    MovieListCard(
                                        modifier = Modifier,
                                        data = it
                                    ) {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                DetailMovieActivity::class.java
                                            ).putExtra(
                                                DetailMovieActivity.EXTRA_MOVIE_ID, it.id
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}