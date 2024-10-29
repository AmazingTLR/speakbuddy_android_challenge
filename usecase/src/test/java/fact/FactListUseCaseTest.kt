package fact

import app.cash.turbine.test
import com.amazingtlr.api.FactRepository
import com.amazingtlr.api.LocalFactRepository
import com.amazingtlr.api.LocalFactResponse
import com.amazingtlr.api.NetworkException
import com.amazingtlr.api.NetworkResult
import com.amazingtlr.api.model.FactListResponse
import com.amazingtlr.api.model.FactResponse
import com.amazingtlr.usecase.UseCaseResult
import com.amazingtlr.usecase.fact.FactListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FactListUseCaseTest {
    private val mockFactRepository: FactRepository = mockk {
        every { observeFacts(any()) } returns flowOf(NetworkResult.Success(fakeFactListResponse))
    }

    private val mockLocalFactRepository: LocalFactRepository = mockk {
        every { observeFacts() } returns flowOf(fakeLocalFactResponseList)
        coEvery { insertAll(any()) } returns Unit
    }

    private val sut: FactListUseCase = FactListUseCase(mockFactRepository, mockLocalFactRepository)

    @Test
    fun `When factListUseCase is invoked, the factRepository is used, localFactRepository is used to insert`() =
        runTest {
            val neededPage = 1

            sut.invoke(neededPage).test {
                val item = awaitItem()
                assert(item is UseCaseResult.Success)
                awaitComplete()
            }

            verify(exactly = 1) {
                mockFactRepository.observeFacts(neededPage)
            }

            coVerify(exactly = 1) {
                mockLocalFactRepository.insertAll(any())
            }

            verify(exactly = 0) {
                mockLocalFactRepository.observeFacts()
            }
        }

    @Test
    fun `When factListUseCase is invoked and factRepository fails, a fallback is done on localFactRepository`() =
        runTest {
            val neededPage = 1

            every {
                mockFactRepository.observeFacts(any())
            } returns flowOf(NetworkResult.Error(NetworkException("Fail for some reason")))


            sut.invoke(neededPage).test {
                val item = awaitItem()
                assert(item is UseCaseResult.Success)
                awaitComplete()
            }

            verify(exactly = 1) {
                mockFactRepository.observeFacts(neededPage)
            }

            verify(exactly = 1) {
                mockLocalFactRepository.observeFacts()
            }
        }

    companion object {
        private val fakeFactListResponse = FactListResponse(
            factList = listOf(
                FactResponse(
                    fact = "A fact",
                    length = 6
                ),
                FactResponse(
                    fact = "Another fact",
                    length = 12
                )
            ),
            currentPage = 1,
            totalPages = 1
        )

        private val fakeLocalFactResponseList = listOf(
            LocalFactResponse("1","A local fact", 6),
            LocalFactResponse("2", "Another local fact", 12)
        )
    }
}