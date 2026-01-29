package com.example.mangary3.presentation.screen.mangahomescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mangary3.presentation.screen.mangahomescreen.components.CategoryChips
import com.example.mangary3.presentation.screen.mangahomescreen.components.MangaCarousel
import com.example.mangary3.presentation.screen.mangahomescreen.components.MangaPager
import com.example.mangary3.presentation.screen.mangahomescreen.components.MangaSearchBar
import com.example.mangary3.presentation.viewmodel.mangahomeviewmodel.MangaHomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MangaHomeScreen(
    mangaHomeViewModel: MangaHomeViewModel = hiltViewModel(),
    onClick: () -> Unit = { },
    onMangaCarouselClick: () -> Unit = {}
) {
    val modifier = Modifier.padding(horizontal = 16.dp)
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item { MangaSearchBar() }

        item { CategoryChips(modifier = modifier) }

        item { MangaPager(modifier = modifier, onClick = onClick) }

        item {
            MangaCarousel(modifier, title = "Trending", viewModel = mangaHomeViewModel) {
                onMangaCarouselClick()
            }
        }
    }
}

@Preview
@Composable
fun MangaHomeScreenPreview() {
    MangaHomeScreen()
}