package com.example.mangary3.presentation.screen.mangahomescreen.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ContextualFlowRowOverflowScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.example.mangary3.data.local.entities.manga.mangaCategories

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryChips(
    modifier: Modifier
) {
    val mangaCategorySize = mangaCategories.size
    var maxLines by remember { mutableIntStateOf(2) }
    val moreOrCollapseIndicator = @Composable { scope: ContextualFlowRowOverflowScope ->
        val remainingItems = mangaCategorySize - scope.shownItemCount
        AssistChip(label = {
            Text(if (remainingItems == 0) "Less" else "+$remainingItems")
        }, onClick = {
            if (remainingItems == 0) {
                maxLines = 2
            } else {
                maxLines += 5
            }
        })
    }
    //Dummy categories
    //val categories = listOf("Action", "Love",
    // "Adventure", "Horror", "Mystery", "Comedy", "School life", "Drama")
    //Different way to handle the end of the line than regular Row/LazyRow
    //ContextualFlowRow: Lazy FlowRow
    ContextualFlowRow(
        modifier = modifier,
        itemCount = mangaCategories.size,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        overflow = ContextualFlowRowOverflow.expandOrCollapseIndicator(
            minRowsToShowCollapse = 4,
            expandIndicator = moreOrCollapseIndicator,
            collapseIndicator = moreOrCollapseIndicator
        ),
        maxLines = maxLines
    ) { index ->
        AssistChip(onClick = {
            Log.d("CategoryChips", "Category clicked: $mangaCategories[index]")
        }, label = { Text(mangaCategories[index]) })
    }
}
