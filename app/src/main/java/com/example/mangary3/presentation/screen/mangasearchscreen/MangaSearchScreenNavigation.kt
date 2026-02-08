package com.example.mangary3.presentation.screen.mangasearchscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val MANGA_SEARCH_ROUTE = "manga_search_screen_key"


fun NavGraphBuilder.mangaSearchScreen(
    onClick: () -> Unit
){
    composable(MANGA_SEARCH_ROUTE){
        MangaSearchScreen(
            onSearchEnter = onClick
        )
    }
}