package com.example.mangary3.presentation.screen.mangahomescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mangary3.R
import com.example.mangary3.core.components.LoadingShimmer
import com.example.mangary3.domain.model.Manga
import com.example.mangary3.presentation.navigation.AppBottomNavigationBar
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
    onMangaCarouselClick: (Manga) -> Unit = {}
) {
    val uiState by mangaHomeViewModel.uiState.collectAsState()
    val tags by mangaHomeViewModel.tags.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()

    if (pullRefreshState.isAnimating) {
        LaunchedEffect(true) {
            mangaHomeViewModel.refresh()
        }
    }

    PullToRefreshBox(
        isRefreshing = uiState is MangaHomeScreenUIState.Refreshing,
        onRefresh = { mangaHomeViewModel.refresh() },
        modifier = Modifier.fillMaxSize()
    ) {
        when (val state = uiState) {
            is MangaHomeScreenUIState.Initial -> {
                // Show initial state or nothing
            }

            is MangaHomeScreenUIState.Loading -> {
                LoadingShimmer()
            }

            is MangaHomeScreenUIState.Success,
            is MangaHomeScreenUIState.Refreshing -> {
                val mangaList = when (state) {
                    is MangaHomeScreenUIState.Success -> state.mangaList
                    is MangaHomeScreenUIState.Refreshing -> state.mangaList
                    else -> emptyList()
                }
                val searchQuery = when (state) {
                    is MangaHomeScreenUIState.Success -> state.searchQuery
                    is MangaHomeScreenUIState.Refreshing -> state.searchQuery
                    else -> ""
                }
                val selectedCategory = when (state) {
                    is MangaHomeScreenUIState.Success -> state.selectedCategory
                    is MangaHomeScreenUIState.Refreshing -> state.selectedCategory
                    else -> "All"
                }

                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(animationSpec = tween(300)),
                    exit = fadeOut()
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        item {
                            AnimatedSearchBar(
                                query = searchQuery,
                                onQueryChange = {  }
                            )
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

                    AppBottomNavigationBar()
                }
            }

            is MangaHomeScreenUIState.Error -> {
                // Show error UI
                Text(
                    text = state.message,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
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
                painter = painterResource(R.drawable.home),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
