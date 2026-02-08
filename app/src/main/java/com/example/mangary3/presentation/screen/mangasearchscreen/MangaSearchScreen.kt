package com.example.mangary3.presentation.screen.mangasearchscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mangary3.presentation.screen.mangasearchscreen.components.AnimatedSearchBar

@Composable
fun MangaSearchScreen(
    onSearchEnter: () -> Unit
) {
    PullToRefreshBox(
        isRefreshing = false,
        onRefresh = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            item {
                AnimatedSearchBar(

                )
            }

            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3)
                ) {

                }
            }
        }
    }
}

@Preview
@Composable
fun MangaSearchScreenPreview(){
    MangaSearchScreen {  }
}