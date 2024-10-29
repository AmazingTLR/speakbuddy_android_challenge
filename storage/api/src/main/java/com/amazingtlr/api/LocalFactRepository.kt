package com.amazingtlr.api

import kotlinx.coroutines.flow.Flow


interface LocalFactRepository {
    fun observeFacts(): Flow<List<LocalFactResponse>>
    suspend fun insertAll(facts: List<LocalFactResponse>)
    suspend fun clearAll()
    suspend fun markFactAsSeen(factId: String)
    fun observeSeenFacts(): Flow<List<LocalFactResponse>>
}