import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import jp.speakbuddy.edisonandroidexercise.MainActivity
import jp.speakbuddy.edisonandroidexercise.TestTags
import jp.speakbuddy.edisonandroidexercise.model.FactUI
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactListSuccessScreen
import org.junit.Rule
import org.junit.Test

class FactListSuccessScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun fabHistoryButton_click_shouldInvokeCallback() {
        var wasClicked = false

        composeTestRule.activity.setContent {
            FactListSuccessScreen(
                factList = listOf(fakeFactUI1),
                onRequestForNextPage = {},
                hasMore = false,
                onFactSeen = {},
                onNavigateToFactHistoryList = { wasClicked = true }
            )
        }

        composeTestRule
            .onNodeWithTag(TestTags.factListScreenFab)
            .performClick()

        assert(wasClicked)
    }

    companion object {
        val fakeFactUI1 = FactUI(
            id = "1",
            fact = "Fact 1",
            length = 10,
            shouldDisplayLength = false,
            multipleCats = false
        )
    }
}