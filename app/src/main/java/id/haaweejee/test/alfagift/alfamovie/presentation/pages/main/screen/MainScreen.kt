package id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.screen

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
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
import id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.home.HomeContentOrganismComponent
import id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.home.HomeContentOrganismComponentEvent
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

    LaunchedEffect(Unit) {
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
            if (!isConnected && isLoaded && (state.discoverMovie as? ViewState.ViewSuccess)?.data.isNullOrEmpty()) {
                ErrorAnimation(
                    lottie = R.raw.no_internet,
                    title = "No Internet",
                    message = "Please Check your Internet Connection",
                    actionText = "Retry",
                    navigateText = "Go To Settings",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp),
                    onAction = {
                        viewModel.getDiscoverMovies()
                    },
                    onNavigate = {
                        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                        context.startActivity(intent)
                    }
                )
            } else {
                PullToRefreshBox(
                    isRefreshing = isLoaded && state.isRefreshing,
                    onRefresh = {
                        viewModel.getDiscoverMovies(isPullRefresh = true)
                    }
                ) {
                    HomeContentOrganismComponent(
                        state = state.discoverMovie,
                        listState = listState,
                        gridState = gridState,
                        isGridViewActive = isGridViewActive,
                        isConnected = isConnected
                    ) { event ->
                        when (event) {
                            is HomeContentOrganismComponentEvent.OnClick -> {
                                context.startActivity(
                                    Intent(
                                        context,
                                        DetailMovieActivity::class.java
                                    ).putExtra(
                                        DetailMovieActivity.EXTRA_MOVIE_ID, event.id
                                    )
                                )
                            }

                            HomeContentOrganismComponentEvent.OnRetry -> viewModel.getDiscoverMovies()
                        }
                    }
                }

            }
        }
    }

}