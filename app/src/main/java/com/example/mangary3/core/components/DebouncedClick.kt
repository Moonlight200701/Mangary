package com.example.mangary3.core.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun rememberDebouncedClick(
    debounceInterval: Long = 3000L,
    onClick: () -> Unit = {},
): () -> Unit {
    val coroutineScope = rememberCoroutineScope()
    val latestOnClick by rememberUpdatedState(onClick)
    var enabled by remember { mutableStateOf(false) }
    return remember {
        {
            if (enabled) {
                enabled = false
                latestOnClick()
                coroutineScope.launch {
                    delay(debounceInterval)
                    enabled = true
                }
            }
        }
    }
}