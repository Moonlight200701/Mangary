package com.example.mangary3.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mangary3.core.constants.APIConstants
import com.example.mangary3.presentation.screen.mangadetailscreen.mangaDetailScreen
import com.example.mangary3.presentation.screen.mangahomescreen.mangaHomeScreen
import com.example.mangary3.presentation.screen.mangasearchscreen.mangaSearchScreen

@Composable
fun NavigateScreen(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = APIConstants.MANGA_HOME_SCREEN,
        modifier = Modifier
    ) {
        mangaHomeScreen(
            onClick = {
                navController.navigate(APIConstants.MANGA_DETAIL_SCREEN)
            },
            onSearchClick = {
                navController.navigate(APIConstants.MANGA_SEARCH_SCREEN)
            }
        )
        mangaDetailScreen {
            navController.popBackStack()
        }
        mangaSearchScreen {
            navController.popBackStack()
        }
    }

}