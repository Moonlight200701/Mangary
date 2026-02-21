package com.example.mangary3.presentation.screen.mangahomescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mangary3.R
import com.example.mangary3.core.components.LoadingShimmer
import com.example.mangary3.domain.model.Manga
import com.example.mangary3.presentation.screen.mangahomescreen.components.AnimatedCategoryChips
import com.example.mangary3.presentation.screen.mangahomescreen.components.AnimatedMangaCarousel
import com.example.mangary3.presentation.screen.mangahomescreen.components.AnimatedMangaPager
import com.example.mangary3.presentation.screen.mangahomescreen.mangahomeviewmodel.MangaHomeScreenUIState
import com.example.mangary3.presentation.screen.mangahomescreen.mangahomeviewmodel.MangaHomeViewModel
import com.example.mangary3.presentation.screen.mangasearchscreen.components.AnimatedSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaHomeScreen(
    mangaHomeViewModel: MangaHomeViewModel = hiltViewModel(),
    onClick: () -> Unit = { },
    onMangaCarouselClick: (Manga) -> Unit = {},
    onSearchClick: () -> Unit = {}
) {
    val uiState by mangaHomeViewModel.uiState.collectAsState()
    val tags by mangaHomeViewModel.tags.collectAsState()
    val networkStatus by mangaHomeViewModel.networkStatus.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()

    if (pullRefreshState.isAnimating) {
        LaunchedEffect(true) {
            mangaHomeViewModel.refresh()
        }
    }

    MangaHomeScreenContent(
        mangaNetworkState = networkStatus,
        uiState = uiState,
        mangaHomeViewModel = mangaHomeViewModel,
        onSearchClick = onSearchClick,
        tags = tags,
        onClick = onClick,
        onMangaCarouselClick = onMangaCarouselClick
    )
}

@Composable
private fun MangaHomeScreenContent(
    mangaNetworkState: Boolean = false,
    uiState: MangaHomeScreenUIState,
    mangaHomeViewModel: MangaHomeViewModel,
    tags: List<String>,
    onSearchClick: () -> Unit,
    onClick: () -> Unit,
    onMangaCarouselClick: (Manga) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        NetworkBanner(mangaNetworkState)

        PullToRefreshBox(
            isRefreshing = uiState is MangaHomeScreenUIState.Refreshing,
            onRefresh = { mangaHomeViewModel.refresh() },
            modifier = Modifier.fillMaxSize()
        ) {
            when (uiState) {
                is MangaHomeScreenUIState.Initial -> {
                    // Show initial state or nothing
                }

                is MangaHomeScreenUIState.Loading -> {
                    LoadingShimmer()
                }

                is MangaHomeScreenUIState.Success,
                is MangaHomeScreenUIState.Refreshing -> {
                    val mangaList = when (uiState) {
                        is MangaHomeScreenUIState.Success -> uiState.mangaList
                        is MangaHomeScreenUIState.Refreshing -> uiState.mangaList
                        else -> emptyList()
                    }
                    val searchQuery = when (uiState) {
                        is MangaHomeScreenUIState.Success -> uiState.searchQuery
                        is MangaHomeScreenUIState.Refreshing -> uiState.searchQuery
                        else -> ""
                    }
                    val selectedCategory = when (uiState) {
                        is MangaHomeScreenUIState.Success -> uiState.selectedCategory
                        is MangaHomeScreenUIState.Refreshing -> uiState.selectedCategory
                        else -> "All"
                    }
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        item {
                            Box(
                                modifier = Modifier.clickable { onSearchClick() }
                            ) {
                                AnimatedSearchBar(
                                    query = searchQuery,
                                    onQueryChange = { },
                                    modifier = Modifier.clickable(
                                        onClick = onSearchClick,
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ),
                                    enabled = false
                                )
                            }
                        }

                        item {
                            AnimatedCategoryChips(
                                categories = tags,
                                selectedCategory = selectedCategory,
                                onCategorySelected = { }
                            )
                        }

                        item {
                            AnimatedMangaPager(
                                mangas = mangaList.take(5),
                                onClick = onClick
                            )
                        }

                        item {
                            SectionHeader(title = "Trending Now")
                        }

                        item {
                            AnimatedMangaCarousel(
                                mangas = mangaList,
                                onClick = onMangaCarouselClick
                            )
                        }

                        item {
                            SectionHeader(title = "Popular This Week")
                        }

                        item {
                            AnimatedMangaCarousel(
                                mangas = mangaList,
                                onClick = onMangaCarouselClick
                            )
                        }
                    }
                }

                is MangaHomeScreenUIState.Error -> {
                    // Show error UI
                    Text(
                        text = uiState.message,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
            }
        }

    }

}

@Composable
private fun NetworkBanner(mangaNetworkState: Boolean) {
    AnimatedVisibility(
        visible = !mangaNetworkState,
        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.error)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_wifi_off),
                contentDescription = if (mangaNetworkState) "No Network" else "Network is restored",
                tint = MaterialTheme.colorScheme.onError,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "No internet connection",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onError
            )
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        TextButton(onClick = { /* Navigate to see all */ }) {
            Text("See All")
            Icon(
                painter = painterResource(R.drawable.ic_home),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
