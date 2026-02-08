package com.example.mangary3.presentation.screen.mangahomescreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mangary3.core.constants.MangaConstants
import com.example.mangary3.domain.model.Manga

@Composable
fun AnimatedMangaCarousel(
    modifier: Modifier = Modifier,
    mangas: List<Manga>,
    onClick: (Manga) -> Unit,
) {
    Column(modifier = modifier) {
        if (mangas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(mangas.size, key = { it }) { index ->
                    AnimatedMangaItem(manga = mangas[index], onClick = onClick)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnimatedMangaItem(manga: Manga, onClick: (Manga) -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clickable { onClick(manga) }
            .background(Color.Transparent)
            .animateContentSize(
                animationSpec = tween(durationMillis = 300),
            ), // Adds animation when items are reordered
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GlideImage(
                model = manga.getCoverArtUrl() ?: "",
                contentDescription = manga.attributes.title[MangaConstants.MANGA_NAME_KEY],
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp, 180.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = { onClick(manga) })
            )
            Text(
                text = manga.attributes.title[MangaConstants.MANGA_NAME_KEY] ?: "No title",
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