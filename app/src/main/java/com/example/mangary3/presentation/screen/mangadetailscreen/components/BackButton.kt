package com.example.mangary3.presentation.screen.mangadetailscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mangary3.R

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Button(
        elevation = ButtonDefaults.elevatedButtonElevation(0.dp, 0.dp),
        onClick = {
            onBackClick()
        }
    ) {
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_home),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .size(18.dp)
            )

            Text(
                text = "Back",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 14.sp
            )
        }
    }

}