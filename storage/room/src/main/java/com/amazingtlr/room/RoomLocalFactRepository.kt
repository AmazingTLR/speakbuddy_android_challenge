package com.amazingtlr.room

import com.amazingtlr.api.LocalFactRepository
import com.amazingtlr.api.LocalFactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalFactRepository(private val factFactDao: FactDao) : LocalFactRepository {
    override fun observeFacts(): Flow<List<LocalFactResponse>> = factFactDao.getAllFacts().map {
        it.map { fact -> fact.toLocalFactResponse() }
    }

    override suspend fun insertAll(facts: List<LocalFactResponse>) =
        factFactDao.insertAll(facts.map { it.toFact() })

    override suspend fun clearAll() = factFactDao.clearAll()
}