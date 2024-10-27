package fact

import com.amazingtlr.api.FactRepository
import com.amazingtlr.usecase.fact.FactUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class FactUseCaseTest {
    private val mockFactRepository: FactRepository = mockk {
        every { observeFact() } returns mockk()
    }
    private val sut: FactUseCase = FactUseCase(mockFactRepository)

    @Test
    fun `When factUseCase is invoked, the factRepository is used`() {
        sut.invoke()

        verify(exactly = 1) {
            mockFactRepository.observeFact()
        }
    }
}