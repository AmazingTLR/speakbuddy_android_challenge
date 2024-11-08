package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import jp.speakbuddy.edisonandroidexercise.R
import jp.speakbuddy.edisonandroidexercise.TestTags
import jp.speakbuddy.edisonandroidexercise.model.FactUI
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonTheme

@Composable
fun FactListSuccessScreen(
    factList: List<FactUI>,
    onRequestForNextPage: () -> Unit,
    hasMore: Boolean,
    onFactSeen: (String) -> Unit,
    onNavigateToFactHistoryList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToFactHistoryList,
                containerColor = EdisonTheme.colors.background.default,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(EdisonTheme.dimensions.M),
                modifier = modifier.semantics {
                    testTag = TestTags.factListScreenFab
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = stringResource(id = R.string.cat_history_content_description),
                    tint = EdisonTheme.colors.text.default
                )
            }
        }, content = { scaffoldPadding ->
            FactHorizontalPager(
                factList = factList,
                onRequestForNextPage = onRequestForNextPage,
                hasMore = hasMore,
                onFactSeen = onFactSeen,
                modifier = modifier
                    .consumeWindowInsets(scaffoldPadding) //Needed for having a proper edge-to-edge UX
            )
        })
}

@Composable
@Preview
fun FactListSuccessScreenPreview() {
    FactListSuccessScreen(
        factList = listOf(
            FactUI(
                id = "1",
                fact = "Fact 1",
                length = 10,
                shouldDisplayLength = false,
                multipleCats = false
            ),
        ),
        onRequestForNextPage = {},
        hasMore = true,
        onFactSeen = {},
        onNavigateToFactHistoryList = {}
    )
}