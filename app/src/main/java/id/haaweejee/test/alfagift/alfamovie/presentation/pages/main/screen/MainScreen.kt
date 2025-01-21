package id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.screen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import id.haaweejee.test.alfagift.alfamovie.presentation.common.ViewState
import id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms.GridViewButton
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieGridCard
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieListCard
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.MovieListCardShimmer
import id.haaweejee.test.alfagift.alfamovie.presentation.pages.detail.DetailMovieActivity
import id.haaweejee.test.alfagift.alfamovie.presentation.pages.main.vm.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    var isGridViewActive by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getDiscoverMovies()
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
            when (state.discoverMovie) {
                ViewState.ViewLoading -> {
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

                ViewState.ViewEmpty -> {}
                is ViewState.ViewError -> {}
                is ViewState.ViewFailed -> {

                }

                is ViewState.ViewSuccess -> {
                    if (isGridViewActive) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2), // Number of columns
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(
                                (state.discoverMovie as? ViewState.ViewSuccess)?.data ?: emptyList()
                            ) {
                                MovieGridCard(
                                    modifier = Modifier,
                                    data = it
                                ) {
                                    context.startActivity(
                                        Intent(context, DetailMovieActivity::class.java).putExtra(
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
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(
                                (state.discoverMovie as? ViewState.ViewSuccess)?.data ?: emptyList()
                            ) {
                                MovieListCard(
                                    modifier = Modifier,
                                    data = it
                                ) {
                                    context.startActivity(
                                        Intent(context, DetailMovieActivity::class.java).putExtra(
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