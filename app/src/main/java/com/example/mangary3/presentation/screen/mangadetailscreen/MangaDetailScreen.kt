package com.example.mangary3.presentation.screen.mangadetailscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mangary3.R
import com.example.mangary3.data.local.entities.manga.mangaCategories
import com.example.mangary3.presentation.screen.mangadetailscreen.mangadetailviewmodel.MangaDetailViewModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun MangaDetailScreen(
    mangaId: String = "",
    initialTitle: String = "",
    initialCoverUrl: String = "",
    onBackClick: () -> Unit = {},
    viewModel: MangaDetailViewModel = hiltViewModel()
) {
    // Load full manga details
    LaunchedEffect(mangaId) {
        if (mangaId.isNotEmpty()) {
            viewModel.loadMangaDetails(mangaId)
        }
    }

    val uiState by viewModel.uiState.collectAsState()

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val topWeight = 1f
        val bottomWeight = 3f
        val maxWidth = this.maxWidth
        val maxHeight = this.maxHeight
        val topHeight = maxHeight * (topWeight / (topWeight + bottomWeight))

        Column(modifier = Modifier.fillMaxSize()) {

            // Top section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(topHeight)
                    .background(Color.DarkGray)
            ) {
                IconButton(onClick = onBackClick){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Back",
                    )
                }
            }
            // Bottom section
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .matchParentSize()

                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column() {
                            Spacer(modifier = Modifier.height(110.dp))
                            Button(
                                onClick = {
                                    Log.d("CategoryChips", "Category clicked: Read Now")
                                },
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                Text("Read Now")
                            }
                            Button(
                                onClick = {
                                    Log.d("CategoryChips", "Category clicked: Add to Library")
                                },
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                            ) {
                                Text("Add to Library")
                            }
                        }
                        Column {
                            // Show initial title immediately, update when full data loads
                            Text(
                                text = initialTitle,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                color = Color.White
                            )
                            FlowRow(
                                maxItemsInEachRow = 4,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                            ) {
                                for (category in mangaCategories) {
                                    AssistChip(
                                        onClick = {
                                            Log.d("Category chips", "Clicked on chip $category")
                                        },
                                        label = { Text(category, color = Color.White) },
                                        modifier = Modifier.padding(horizontal = 5.dp)
                                    )
                                }
                            }
                        }

                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Description",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color.White
                        )
                    }

                }
            }
        }

        // Show initial cover immediately, load full quality when available
        GlideImage(
            model = initialCoverUrl.ifEmpty { R.drawable.dummy_image },
            contentDescription = initialTitle,
            modifier = Modifier
                .width(150.dp)
                .height(200.dp)
                .offset(x = 20.dp, y = topHeight - 100.dp),
            contentScale = ContentScale.Crop
        )

    }


}


@Preview
@Composable
fun DummyDetailScreen() {
    MangaDetailScreen()
}