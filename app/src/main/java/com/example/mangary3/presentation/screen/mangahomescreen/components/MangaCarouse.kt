package com.example.mangary3.presentation.screen.mangahomescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mangary3.R
import com.example.mangary3.data.remote.responses.MangaFromAPIDTO
import com.example.mangary3.presentation.viewmodel.mangahomeviewmodel.MangaHomeViewModel

@Composable
fun MangaCarousel(
    modifier: Modifier,
    title: String,
    viewModel: MangaHomeViewModel = hiltViewModel(),
    onClick: () -> Unit,
) {
    val uiState by viewModel.mangaPagerUiState.collectAsState()
    Column {
        Text(title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier.height(8.dp))
        if (uiState.mangaList.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(uiState.mangaList.size) { index ->
                    val currentManga = uiState.mangaList[index]
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        MangaItem(currentManga) {
                            onClick()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MangaItem(manga: MangaFromAPIDTO, onClick: (MangaFromAPIDTO) -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clickable { onClick(manga) }
            .background(Color.Transparent),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.dummy_image),
                contentDescription = "Dummy test",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp, 160.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),

                )
            Text(
                manga.attributes.title["en"] ?: "No title",
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(2.dp)
                    .width(120.dp)
            )
        }
    }
}