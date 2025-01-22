package id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.haaweejee.test.alfagift.alfamovie.R
import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie
import id.haaweejee.test.alfagift.alfamovie.presentation.common.ViewState
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.ErrorAnimation
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieGridCard
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieGridCardShimmer
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieListCard
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieListCardShimmer

@Composable
fun HomeContentOrganismComponent(
    state: ViewState<List<Movie>>,
    isGridViewActive: Boolean,
    gridState: LazyGridState,
    listState: LazyListState,
    isConnected: Boolean,
    onClick: (HomeContentOrganismComponentEvent) -> Unit
) {
    when (state) {
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
            val title = (state as? ViewState.ViewError)?.title.orEmpty()
            val message =
                (state as? ViewState.ViewError)?.message.orEmpty()
            ErrorAnimation(
                lottie = R.raw.error_animation,
                title = title,
                message = message,
                actionText = "Retry",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                onAction = { onClick.invoke(HomeContentOrganismComponentEvent.OnRetry) },
                onNavigate = null
            )
        }

        is ViewState.ViewFailed -> {
            val message =
                (state as? ViewState.ViewFailed)?.exception?.message.orEmpty()
            ErrorAnimation(
                lottie = R.raw.error_animation,
                title = "Something when Wrong",
                message = message,
                actionText = "Retry",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                onAction = {
                    onClick.invoke(HomeContentOrganismComponentEvent.OnRetry)
                },
                onNavigate = null
            )
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
                        (state as? ViewState.ViewSuccess)?.data
                            ?: emptyList()
                    ) {
                        MovieGridCard(
                            modifier = Modifier,
                            data = it
                        ) {
                            onClick.invoke(HomeContentOrganismComponentEvent.OnClick(it.id))
                        }
                    }
                    if (isConnected) {
                        items(2) {
                            MovieGridCardShimmer()
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
                        (state as? ViewState.ViewSuccess)?.data
                            ?: emptyList()
                    ) {
                        MovieListCard(
                            modifier = Modifier,
                            data = it
                        ) {
                            onClick.invoke(HomeContentOrganismComponentEvent.OnClick(it.id))
                        }
                    }
                    item {
                        if (isConnected) {
                            MovieListCardShimmer()
                        }
                    }
                }
            }
        }
    }
}

sealed class HomeContentOrganismComponentEvent {
    data object OnRetry : HomeContentOrganismComponentEvent()
    data class OnClick(val id: Int) : HomeContentOrganismComponentEvent()
}