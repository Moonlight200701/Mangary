package com.example.mangary3.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mangary3.core.constants.APIConstants
import com.example.mangary3.presentation.screen.mangadetailscreen.mangaDetailScreen
import com.example.mangary3.presentation.screen.mangahomescreen.mangaHomeScreen

@Composable
fun NavigateScreen(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = APIConstants.MANGA_HOME_SCREEN,
        modifier = Modifier
    ) {
        mangaHomeScreen {
            navController.navigate(APIConstants.MANGA_DETAIL_SCREEN)
        }
        mangaDetailScreen {
            navController.popBackStack()
        }
    }

}