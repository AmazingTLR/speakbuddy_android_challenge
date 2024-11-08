package fact

import com.amazingtlr.api.LocalFactRepository
import com.amazingtlr.usecase.fact.ClearFactListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ClearFactListUseCaseTest {
    private val mockLocalFactRepository: LocalFactRepository = mockk {
        coEvery { clearAll() } returns mockk()
    }
    private val sut: ClearFactListUseCase = ClearFactListUseCase(mockLocalFactRepository)

    @Test
    fun `When clearFactListUseCase is invoked, the localFactRepository is used`() = runTest{
        sut.invoke()

        coVerify(exactly = 1) {
            mockLocalFactRepository.clearAll()
        }
    }
}