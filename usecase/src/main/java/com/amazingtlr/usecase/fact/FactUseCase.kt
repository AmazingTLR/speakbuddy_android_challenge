package com.amazingtlr.usecase.fact

import com.amazingtlr.api.FactRepository
import com.amazingtlr.api.model.FactResponse
import com.amazingtlr.usecase.UseCaseResult
import com.amazingtlr.usecase.mapNetworkResultToUseCaseResult
import kotlinx.coroutines.flow.Flow

class FactUseCase(private val factRepository: FactRepository) {
    operator fun invoke(): Flow<UseCaseResult<FactResponse, Any>> =
        factRepository.observeFact().mapNetworkResultToUseCaseResult()
}