package fact

import com.amazingtlr.api.FactRepository
import com.amazingtlr.usecase.fact.FactListUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class FactListUseCaseTest {
    private val mockFactRepository: FactRepository = mockk {
        every { observeFacts(any()) } returns mockk()
    }
    private val sut: FactListUseCase = FactListUseCase(mockFactRepository)

    @Test
    fun `When factListUseCase is invoked, the factRepository is used`() {
        val neededPage = 1
        sut.invoke(neededPage)

        verify(exactly = 1) {
            mockFactRepository.observeFacts(neededPage)
        }
    }
}