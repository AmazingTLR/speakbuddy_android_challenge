package jp.speakbuddy.edisonandroidexercise.ui

import app.cash.turbine.test
import com.amazingtlr.api.model.FactListResponse
import com.amazingtlr.api.model.FactResponse
import com.amazingtlr.usecase.UseCaseResult
import com.amazingtlr.usecase.fact.ClearFactListUseCase
import com.amazingtlr.usecase.fact.FactListUseCase
import com.amazingtlr.usecase.fact.MarkFactAsSeenUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.model.FactUI
import jp.speakbuddy.edisonandroidexercise.states.FactListState
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FactViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockFactListUseCase: FactListUseCase = mockk {
        every { this@mockk.invoke(1) } returns flowOf(UseCaseResult.Success(fakeFactListResponse1))
        every { this@mockk.invoke(2) } returns flowOf(UseCaseResult.Success(fakeFactListResponse2))
    }
    private val mockMarkFactAsSeenUseCase: MarkFactAsSeenUseCase = mockk {
        coEvery { this@mockk.invoke(any()) } returns Unit
    }
    private val mockClearFactListUseCase: ClearFactListUseCase = mockk(relaxed = true)
    private val mockFactUITransformer: FactUITransformer = mockk {
        every { this@mockk.invoke(fakeFactListResponse1.factList[0]) } returns fakeFactUI1
        every { this@mockk.invoke(fakeFactListResponse2.factList[0]) } returns fakeFactUI2
    }

    private lateinit var sut: FactViewModel

    @Before
    fun setup() {
        sut = FactViewModel(
            factListUseCase = mockFactListUseCase,
            markFactAsSeenUseCase = mockMarkFactAsSeenUseCase,
            clearFactUseCase = mockClearFactListUseCase,
            factUITransformer = mockFactUITransformer
        )
    }

    @Test
    fun `When initializing, the factListStateFlow has data`() = runTest {
        sut.factListStateFlow.test {
            skipItems(1) // Skip the initial Loading state
            val result = awaitItem()
            assert(result is FactListState.Success)
            assert((result as FactListState.Success).factList.size == 1)
        }

        coVerify(exactly = 1) {
            mockClearFactListUseCase()
        }
    }


    @Test
    fun `When loading more facts, the factListStateFlow is updated`() = runTest {
        sut.factListStateFlow.test {
            skipItems(1) // Skip the initial Loading state

            val firstPageOfItems = awaitItem()
            assert((firstPageOfItems as FactListState.Success).hasMoreContent)
            assert(firstPageOfItems.factList.size == 1) // Only one item when loading the first page

            sut.loadMoreFacts()

            val secondPageOfItems = awaitItem()
            assert(!(secondPageOfItems as FactListState.Success).hasMoreContent)
            assert(secondPageOfItems.factList.size == 2) // Two items when loading more facts

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `When markFactAsSeen is called, the markFactAsSeenUseCase is invoked`() = runTest {
        val factId = "1"

        sut.markFactAsSeen(factId = factId)
        advanceUntilIdle()

        coVerify(exactly = 1) {
            mockMarkFactAsSeenUseCase(factId = factId)
        }
    }

    companion object {
        val fakeFactListResponse1 = FactListResponse(
            factList = listOf(
                FactResponse(
                    id = "1",
                    fact = "Fact 1",
                    length = 6
                )
            ),
            currentPage = 1,
            totalPages = 2
        )

        val fakeFactUI1 = FactUI(
            id = "1",
            fact = "Fact 1",
            length = 6,
            shouldDisplayLength = false,
            multipleCats = false
        )

        val fakeFactListResponse2 = FactListResponse(
            factList = listOf(
                FactResponse(
                    id = "2",
                    fact = "Fact 2",
                    length = 6
                )
            ),
            currentPage = 2,
            totalPages = 2
        )

        val fakeFactUI2 = FactUI(
            id = "2",
            fact = "Fact 2",
            length = 6,
            shouldDisplayLength = false,
            multipleCats = false
        )
    }
}
