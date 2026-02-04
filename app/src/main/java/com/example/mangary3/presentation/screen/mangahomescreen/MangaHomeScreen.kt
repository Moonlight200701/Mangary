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
import com.example.mangary3.presentation.screen.mangahomescreen.components.AnimatedCategoryChips
import com.example.mangary3.presentation.screen.mangahomescreen.components.AnimatedMangaCarousel
import com.example.mangary3.presentation.screen.mangahomescreen.components.AnimatedMangaPager
import com.example.mangary3.presentation.screen.mangahomescreen.components.AnimatedSearchBar
import com.example.mangary3.presentation.viewmodel.mangahomeviewmodel.MangaHomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaHomeScreen(
    mangaHomeViewModel: MangaHomeViewModel = hiltViewModel(),
    onClick: () -> Unit = { },
    onMangaCarouselClick: (Manga) -> Unit = {}
) {
    val uiState by mangaHomeViewModel.mangaPagerUiState.collectAsState()
    val tags by mangaHomeViewModel.tags.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()

    if (pullRefreshState.isAnimating) {
        LaunchedEffect(true) {
            mangaHomeViewModel.refresh()
        }
    }

    PullToRefreshBox (
        isRefreshing = uiState.isRefreshing,
        onRefresh = { mangaHomeViewModel.refresh() },
        modifier = Modifier
            .fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = !uiState.isLoading,
            enter = fadeIn(animationSpec = tween(300))
//                    + slideInVertically(
//                initialOffsetY = { it / 4 }
//            ),
                    ,
            exit = fadeOut()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    AnimatedSearchBar(
                        query = uiState.searchQuery,
                        onQueryChange = { mangaHomeViewModel.updateSearchQuery(it) }
                    )
                }

                item {
                    AnimatedCategoryChips(
                        categories = tags,
                        selectedCategory = uiState.selectedCategory,
                        onCategorySelected = { mangaHomeViewModel.updateCategory(it) }
                    )
                }

                item {
                    AnimatedMangaPager(
                        mangas = uiState.mangaList.take(5),
                        onClick = onClick
                    )
                }

                item {
                    SectionHeader(title = "Trending Now")
                }

                item {
                    AnimatedMangaCarousel(
                        mangas = uiState.mangaList,
                        onClick = onMangaCarouselClick
                    )
                }

                item {
                    SectionHeader(title = "Popular This Week")
                }

                item {
                    AnimatedMangaCarousel(
                        mangas = uiState.mangaList.reversed(),
                        onClick = onMangaCarouselClick
                    )
                }
            }
        }

        if (uiState.isLoading) {
            LoadingShimmer()
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
