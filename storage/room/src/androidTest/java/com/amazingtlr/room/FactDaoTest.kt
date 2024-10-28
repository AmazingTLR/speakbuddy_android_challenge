package com.amazingtlr.room

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class FactDaoTest {
    private lateinit var factDao: FactDao
    private lateinit var database: RoomFactDatabase

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Use an in-memory database for testing purposes
        database = Room.inMemoryDatabaseBuilder(appContext, RoomFactDatabase::class.java)
            .allowMainThreadQueries() // For testing only
            .build()
        factDao = database.factDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun testFactInsertion() = runTest {
        val fact = FactEntity(id = "1", fact = "Fact 1", length = 10)
        factDao.insertAll(listOf(fact))

        val item = factDao.getAllFacts().first()

        advanceUntilIdle()

        assert(item.size == 1)
        assert(item[0].id == "1")
        assert(item[0].fact == "Fact 1")
        assert(item[0].length == 10)
    }

    @Test
    fun testFactClear() = runTest {
        val fact = FactEntity(id = "1", fact = "Fact 1", length = 10)
        factDao.insertAll(listOf(fact))

        var item = factDao.getAllFacts().first()
        advanceUntilIdle()
        assert(item.size == 1)

        factDao.clearAll()

        item = factDao.getAllFacts().first()
        advanceUntilIdle()
        assert(item.isEmpty())
    }

}