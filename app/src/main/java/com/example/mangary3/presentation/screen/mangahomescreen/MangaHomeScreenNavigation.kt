package com.example.mangary3.presentation.screen.mangahomescreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mangary3.core.constants.APIConstants
import com.example.mangary3.domain.model.Manga

fun NavGraphBuilder.mangaHomeScreen(
    onClick: () -> Unit,
    onSearchClick: () -> Unit,
    onMangaCarouselClick: (Manga) -> Unit
) {
    composable(route = APIConstants.MANGA_HOME_SCREEN){
        MangaHomeScreen(
            onClick = onClick,
            onSearchClick = onSearchClick,
            onMangaCarouselClick = onMangaCarouselClick
        )
    }
}