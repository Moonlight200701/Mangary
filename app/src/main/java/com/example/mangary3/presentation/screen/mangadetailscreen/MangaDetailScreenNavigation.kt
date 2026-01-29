package com.example.mangary3.presentation.screen.mangadetailscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mangary3.core.Constants

fun NavGraphBuilder.mangaDetailScreen(
    onBackClick: () -> Unit
) {
    composable(route = Constants.MANGA_DETAIL_SCREEN) {
        MangaDetailScreen()
    }
}