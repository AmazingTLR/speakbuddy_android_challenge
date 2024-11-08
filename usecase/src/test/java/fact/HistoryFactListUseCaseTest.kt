package fact

import com.amazingtlr.api.LocalFactRepository
import com.amazingtlr.usecase.fact.HistoryFactListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HistoryFactListUseCaseTest {
    private val mockLocalFactRepository: LocalFactRepository = mockk {
        coEvery { observeSeenFacts() } returns mockk()
    }
    private val sut: HistoryFactListUseCase = HistoryFactListUseCase(mockLocalFactRepository)

    @Test
    fun `When historyFactListUseCaseTest is invoked, the localFactRepository is used`() = runTest{
        sut.invoke()

        coVerify(exactly = 1) {
            mockLocalFactRepository.observeSeenFacts()
        }
    }
}