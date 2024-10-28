package com.amazingtlr.room

import com.amazingtlr.api.LocalFactResponse
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RoomLocalFactRepositoryTest {
    private val mockFactDao: FactDao = mockk(relaxed = true)

    private val sut: RoomLocalFactRepository = RoomLocalFactRepository(mockFactDao)

    @Test
    fun `When repository is observing facts, factDao is called`() {
        sut.observeFacts()

        verify(exactly = 1) {
            mockFactDao.getAllFacts()
        }
    }

    @Test
    fun `When repository is inserting facts, factDao is called`() = runTest {
        val facts = listOf(LocalFactResponse(fact = "Fact 1", length = 10))

        sut.insertAll(facts)

        coVerify(exactly = 1) {
            mockFactDao.insertAll(any())
        }
    }

    @Test
    fun `When clearing facts, factDao is called`() = runTest {
        sut.clearAll()

        coVerify(exactly = 1) {
            mockFactDao.clearAll()
        }
    }
}