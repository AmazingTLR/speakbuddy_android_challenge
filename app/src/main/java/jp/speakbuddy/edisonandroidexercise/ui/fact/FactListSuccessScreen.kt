package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.speakbuddy.edisonandroidexercise.model.FactUI

@Composable
fun FactListSuccessScreen(
    factList: List<FactUI>,
    onRequestForNextPage: () -> Unit,
    hasMore: Boolean,
    modifier: Modifier = Modifier
) {
    FactHorizontalPager(
        factList = factList,
        onRequestForNextPage = onRequestForNextPage,
        hasMore = hasMore,
        modifier = modifier
    )
}