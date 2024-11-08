package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.speakbuddy.edisonandroidexercise.model.FactUI
import jp.speakbuddy.edisonandroidexercise.ui.common.composable.BulletPagerIndicator
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonColor

private val colorList = listOf(
    EdisonColor.color_background_1,
    EdisonColor.color_background_2,
    EdisonColor.color_background_3,
    EdisonColor.color_background_4,
    EdisonColor.color_background_5,
    EdisonColor.color_background_6,
    EdisonColor.color_background_7,
    EdisonColor.color_background_8,
    EdisonColor.color_background_9,
    EdisonColor.color_background_10,
    EdisonColor.color_background_11,
    EdisonColor.color_background_12,
    EdisonColor.color_background_13,
    EdisonColor.color_background_14
)

private const val numberOfPagerBullets = 4

@Composable
fun FactHorizontalPager(
    factList: List<FactUI>,
    onRequestForNextPage: () -> Unit,
    hasMore: Boolean,
    onFactSeen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = {
        factList.size
    })
    var randomColor by remember { mutableStateOf(colorList.first()) }

    // Monitor page changes and load more items if needed
    LaunchedEffect(pagerState.currentPage) {
        // When user is near the end of the list, load more items
        // The threshold is 3 items from the end
        if (pagerState.currentPage >= factList.size - 3 && hasMore) {
            onRequestForNextPage()
        }

        randomColor = colorList.random()

        onFactSeen(factList[pagerState.currentPage].id)
    }

    val backgroundColor by animateColorAsState(
        targetValue = randomColor,
        animationSpec = tween(durationMillis = 1000),
        label = "backgroundColorLabel"
    )
    Box {
        HorizontalPager(
            state = pagerState,
            modifier = modifier
                .background(backgroundColor)
                .fillMaxSize()
        ) { index ->
            CatCardFact(factUI = factList[index], modifier = modifier)
        }

        BulletPagerIndicator(
            numberOfPagerBullets = numberOfPagerBullets,
            currentPage = pagerState.currentPage,
            modifier = modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun FactHorizontalPagerPreview() {
    FactHorizontalPager(
        factList = listOf(
            FactUI(
                id = "1",
                fact = "Fact 1",
                length = 6,
                shouldDisplayLength = false,
                multipleCats = false
            )
        ),
        onRequestForNextPage = {},
        hasMore = true,
        onFactSeen = {}
    )
}