package jp.speakbuddy.edisonandroidexercise.ui

import app.cash.turbine.test
import com.amazingtlr.api.model.FactResponse
import com.amazingtlr.usecase.UseCaseResult
import com.amazingtlr.usecase.fact.HistoryFactListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.states.HistoryFactListState
import jp.speakbuddy.edisonandroidexercise.ui.history.HistoryFactViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HistoryFactViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockHistoryFactListUseCase: HistoryFactListUseCase = mockk {
        coEvery { this@mockk() } returns flowOf(UseCaseResult.Success(fakeFactListResponse1))
    }
    private val mockFactUITransformer: FactUITransformer = mockk(relaxed = true)

    private lateinit var sut: HistoryFactViewModel

    @Before
    fun setup() {
        sut = HistoryFactViewModel(
            historyFactListUseCase = mockHistoryFactListUseCase,
            factUITransformer = mockFactUITransformer
        )
    }

    @Test
    fun `When initializing, the historyFactListStateFlow has data`() = runTest {
        sut.historyFactListStateFlow.test {
             skipItems(1) // Skip the initial Loading state

             val result = awaitItem()

            assert(result is HistoryFactListState.Success)
             assert((result as HistoryFactListState.Success).factList.size == 1)
        }
    }

    companion object {
        private val fakeFactListResponse1 = listOf(
            FactResponse(
                id = "1",
                fact = "fact1",
                length = 1
            )
        )
    }
}