package com.example.mangary3.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mangary3.core.Constants
import com.example.mangary3.presentation.screen.mangadetailscreen.mangaDetailScreen
import com.example.mangary3.presentation.screen.mangahomescreen.mangaHomeScreen

@Composable
fun NavigateScreen(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Constants.MANGA_HOME_SCREEN,
        modifier = Modifier
    ) {
        mangaHomeScreen {
            navController.navigate(Constants.MANGA_DETAIL_SCREEN)
        }
        mangaDetailScreen {
            navController.popBackStack()
        }
    }

}