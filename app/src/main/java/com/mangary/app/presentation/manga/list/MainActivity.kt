package com.mangary.app.presentation.manga.list

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mangary.app.domain.model.Manga
import com.mangary.app.presentation.theme.MangaryTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity displaying list of manga with Jetpack Compose
 * Part of presentation layer - handles UI interaction
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val viewModel: MangaListViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MangaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MangaListScreenWrapper(
                        viewModel = viewModel,
                        onMangaClick = { manga ->
                            // TODO: Navigate to manga detail screen
                            Toast.makeText(
                                this,
                                "Selected: ${manga.title}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun MangaListScreenWrapper(
    viewModel: MangaListViewModel,
    onMangaClick: (Manga) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    MangaListScreen(
        uiState = uiState,
        onSearchQuery = { query -> viewModel.searchManga(query) },
        onRetry = { viewModel.retry() },
        onRefresh = { viewModel.retry() },
        onMangaClick = onMangaClick
    )
}

