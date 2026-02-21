package com.example.mangary3.presentation.screen.mangadetailscreen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mangary3.R
import com.example.mangary3.data.local.entities.manga.mangaCategories

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MangaDetailScreen(
    onBackClick: () -> Unit = {}
) {
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = 0.dp)
                        .size(16.dp)
                        .clickable(onClick = onBackClick),
                )
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
                            Text(
                                text = "MangaName",
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

        // Image overlapping dynamically
        Image(
            painter = painterResource(id = R.drawable.dummy_image),
            contentDescription = "Manga Image",
            modifier = Modifier
                .width(150.dp)
                .height(200.dp)
                .offset(x = 20.dp, y = topHeight - 100.dp), // valid inside scope
            contentScale = ContentScale.Crop
        )

    }


}


@Preview
@Composable
fun DummyDetailScreen() {
    MangaDetailScreen()
}