package com.example.mangary3.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mangary3.R
import com.example.mangary3.core.Constants

@Composable
fun AppBottomNavigationBar(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(64.dp)
                .shadow(
                    elevation = 15.dp,
                    shape = RoundedCornerShape(25.dp),
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                )
                .clip(RoundedCornerShape(25.dp))
                .background(Color(0xFF1A1A1A))
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val items = listOf(
                    Triple("Home", R.drawable.home, Constants.MANGA_HOME_SCREEN),
                    Triple("Detail", R.drawable.menu, Constants.MANGA_DETAIL_SCREEN)
                )

                items.forEach { currentItem ->
                    IconButton(onClick = { navController.navigate(currentItem.third) }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(currentItem.second),
                            contentDescription = currentItem.first
                        )
                    }
                }
            }
        }
    }
}
