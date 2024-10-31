import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import jp.speakbuddy.edisonandroidexercise.MainActivity
import jp.speakbuddy.edisonandroidexercise.TestTags
import jp.speakbuddy.edisonandroidexercise.model.FactUI
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactHorizontalPager
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactListSuccessScreen
import org.junit.Rule
import org.junit.Test

class FactHorizontalPagerTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun factHorizontalPager_swipe_shouldDisplayNextFact() {
        composeTestRule.activity.setContent {
            FactHorizontalPager(
                factList = listOf(fakeFactUI1, fakeFactUI2),
                onRequestForNextPage = {},
                hasMore = false,
                onFactSeen = {}
            )
        }

        composeTestRule.onNodeWithText(fakeFactUI1.fact).assertIsDisplayed()

        composeTestRule.onRoot().performTouchInput {
            swipeLeft()
        }

        composeTestRule.onNodeWithText(fakeFactUI2.fact).assertIsDisplayed()
    }

    @Test
    fun factHorizontalPager_swipe_shouldMarkFactAsSeen() {
        var markSeen = false
        composeTestRule.activity.setContent {
            FactHorizontalPager(
                factList = listOf(fakeFactUI1, fakeFactUI2),
                onRequestForNextPage = { },
                hasMore = true,
                onFactSeen = { markSeen = true }
            )
        }


        composeTestRule.onRoot().performTouchInput {
            swipeLeft()
        }

        assert(markSeen)
    }

    @Test
    fun factHorizontalPager_swipe_shouldLoad() {
        var loadNextPage = false
        composeTestRule.activity.setContent {
            FactHorizontalPager(
                factList = listOf(fakeFactUI1, fakeFactUI2, fakeFactUI3),
                onRequestForNextPage = { loadNextPage = true },
                hasMore = true,
                onFactSeen = {}
            )
        }


        composeTestRule.onRoot().performTouchInput {
            swipeLeft()
            swipeLeft()
            swipeLeft()
        }

        assert(loadNextPage)
    }

    companion object {
        val fakeFactUI1 = FactUI(
            id = "1",
            fact = "Fact 1",
            length = 6,
            shouldDisplayLength = false,
            multipleCats = false
        )

        val fakeFactUI2 = FactUI(
            id = "2",
            fact = "Fact 2",
            length = 6,
            shouldDisplayLength = false,
            multipleCats = false
        )

        val fakeFactUI3 = FactUI(
            id = "3",
            fact = "Fact 3",
            length = 6,
            shouldDisplayLength = false,
            multipleCats = false
        )
    }
}