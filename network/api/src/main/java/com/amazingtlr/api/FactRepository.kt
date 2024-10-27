package com.amazingtlr.api

import com.amazingtlr.api.model.FactResponse
import kotlinx.coroutines.flow.Flow

interface FactRepository {
    fun observeFact(): Flow<NetworkResult<FactResponse>>
}