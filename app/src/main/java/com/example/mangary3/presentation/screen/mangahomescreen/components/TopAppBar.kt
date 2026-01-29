package com.example.mangary3.presentation.screen.mangahomescreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.mangary3.R

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Icon(painter = painterResource(id = R.drawable.ic_sale), contentDescription = "Sale")
//        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier = Modifier.height(50.dp))
        Row {
            Icon(
                //ToDo: Replace with another icon
                painter = painterResource(R.drawable.home),
                contentDescription = "Profile",
                modifier = Modifier.clickable(true) {
                })
            Spacer(Modifier.weight(1f))
            Icon(painter = painterResource(R.drawable.home), contentDescription = "Theme")
        }
    }
}
