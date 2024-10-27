package jp.speakbuddy.edisonandroidexercise.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import jp.speakbuddy.edisonandroidexercise.model.FactUI

@Composable
fun FactListSuccessScreen(
    factList: List<FactUI>,
    onRequestForNextPage: () -> Unit,
    hasMore: Boolean,
    modifier: Modifier = Modifier
) {
    val lazyColumnState = rememberLazyListState()
    var isLoadingMore by remember { mutableStateOf(false) }

    lazyColumnState.OnBottomReached(buffer = 3, hasMore = hasMore) {
        onRequestForNextPage()
        isLoadingMore = true
    }

    Column {
        LazyColumn(state = lazyColumnState) {
            items(factList) { fact ->
                Text(
                    text = fact.fact,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            if (isLoadingMore && hasMore) {
                item {
                    LoadingCell(modifier = modifier)
                }
            }
        }
    }
}