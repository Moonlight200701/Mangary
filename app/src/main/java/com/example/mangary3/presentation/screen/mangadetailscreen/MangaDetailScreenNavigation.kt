package com.example.mangary3.presentation.screen.mangadetailscreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mangary3.core.constants.APIConstants
import com.example.mangary3.core.constants.MangaConstants
import timber.log.Timber

fun NavGraphBuilder.mangaDetailScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = APIConstants.MANGA_DETAIL_SCREEN,
        arguments = listOf(
            navArgument(MangaConstants.MANGA_ID_KEY) {
                type = NavType.StringType
            },
            navArgument(MangaConstants.MANGA_TITLE_KEY) {
                type = NavType.StringType
            },
            navArgument(MangaConstants.MANGA_COVER_URL_KEY) {
                type = NavType.StringType
            }
        )
    ) {
        val mangaId = it.arguments?.getString(MangaConstants.MANGA_ID_KEY) ?: ""
        val mangaTitle = it.arguments?.getString(MangaConstants.MANGA_TITLE_KEY) ?: ""
        val mangaCoverUrl = it.arguments?.getString(MangaConstants.MANGA_COVER_URL_KEY) ?: ""

        Timber.d("Received Manga ID: $mangaId")
        Timber.d("Received Manga Title (decoded): $mangaTitle")
        Timber.d("Received Cover URL (decoded): $mangaCoverUrl")

        MangaDetailScreen(
            mangaId = mangaId,
            initialTitle = mangaTitle,
            initialCoverUrl = mangaCoverUrl,
            onBackClick = onBackClick
        )
    }
}