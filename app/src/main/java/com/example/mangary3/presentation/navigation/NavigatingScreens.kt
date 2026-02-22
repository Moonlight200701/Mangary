package com.example.mangary3.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mangary3.core.constants.APIConstants
import com.example.mangary3.core.constants.MangaConstants
import com.example.mangary3.presentation.screen.mangadetailscreen.mangaDetailScreen
import com.example.mangary3.presentation.screen.mangahomescreen.mangaHomeScreen
import com.example.mangary3.presentation.screen.mangasearchscreen.mangaSearchScreen
import timber.log.Timber
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NavigateScreen(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = APIConstants.MANGA_HOME_SCREEN,
        modifier = Modifier,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(1000)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(1000)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(1000)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(1000)
            )
        }
    ) {
        mangaHomeScreen(
            onMangaCarouselClick = { manga ->
                val mangaTitle = manga.attributes.title[MangaConstants.MANGA_NAME_KEY] ?: "Unknown"
                val coverUrl = manga.getCoverArtUrl() ?: ""

                // URL encode the parameters to handle special characters
                val encodedCoverUrl = URLEncoder.encode(coverUrl, StandardCharsets.UTF_8.toString())
                Timber.d("Encode Url: $encodedCoverUrl")

                navController.navigate("manga_detail_screen/${manga.id}/$mangaTitle/$encodedCoverUrl")
            },
            onSearchClick = {
                navController.navigate(APIConstants.MANGA_SEARCH_SCREEN)
            },
            onClick = { //Add logic to navigate to manga detail screen
                navController.navigate(APIConstants.MANGA_SEARCH_SCREEN)
            }
        )
        mangaDetailScreen {
            navController.navigateUp()
        }
        mangaSearchScreen {
            navController.navigateUp()
        }
    }

}