package com.example.mangary3.presentation.screen.mangasearchscreen

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val MANGA_SEARCH_ROUTE = "manga_search_screen_key"

fun NavGraphBuilder.mangaSearchScreen(
    onBackClick: () -> Unit
){
    composable(
        route = MANGA_SEARCH_ROUTE,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ){
        MangaSearchScreen(
            onBackClick = onBackClick,
            autoFocusSearch = true
        )
    }
}