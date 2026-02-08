package com.example.mangary3.presentation.screen.mangadetailscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mangary3.core.constants.APIConstants

fun NavGraphBuilder.mangaDetailScreen(
    onBackClick: () -> Unit
) {
    composable(route = APIConstants.MANGA_DETAIL_SCREEN) {
        MangaDetailScreen()
    }
}