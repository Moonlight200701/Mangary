package com.example.mangary3.presentation.screen.mangahomescreen.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mangary3.R
import com.example.mangary3.presentation.state.MangaPagerUiState
import com.example.mangary3.presentation.viewmodel.mangahomeviewmodel.MangaHomeViewModel
import kotlinx.coroutines.launch

@Composable
fun MangaPager(
    modifier: Modifier,
    viewModel: MangaHomeViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    val uiState by viewModel.mangaPagerUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { uiState.mangaList.size })

    Column(
        modifier = modifier
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }

            uiState.errorMessage != null -> {
                Text("Error: ${uiState.errorMessage}")
            }

            uiState.mangaList.isEmpty() -> {
                Text("No manga available.")
            }

            else -> {
                Log.d("MangaPager", "Current UI state: $uiState")
                TrendingPager(pagerState = pagerState, uiState = uiState) { onClick() }
            }
        }

//        Spacer(modifier = Modifier.height(16.dp))

        // Navigation buttons
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        val prevPage = (pagerState.currentPage - 1).coerceAtLeast(0)
                        pagerState.animateScrollToPage(prevPage)
                    }
                },
                enabled = pagerState.currentPage > 0
            ) {

                //ToDo: Replace with another icon
                Icon(
                    painter = painterResource(R.drawable.home),
                    contentDescription = null
                )
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        val nextPage =
                            (pagerState.currentPage + 1).coerceAtMost(uiState.mangaList.size - 1)
                        pagerState.animateScrollToPage(nextPage)
                    }
                },
                enabled = pagerState.currentPage < uiState.mangaList.size - 1
            ) {
                Icon(
                    //ToDo: Replace with another icon
                    painter = painterResource(R.drawable.home),
                    contentDescription = null
                )
            }
        }
    }
}


@Composable
fun TrendingPager(pagerState: PagerState, uiState: MangaPagerUiState, onClick: () -> Unit) {
    HorizontalPager(state = pagerState) { page ->
        val manga = uiState.mangaList[page]
        Column() {
            Text(
                text = manga.attributes.title["en"] ?: "No title",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.clickable {
                    Log.d(
                        "MangaPager",
                        "Clicked on manga ${manga.attributes.title["en"]}"
                    )
                    onClick()
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}





