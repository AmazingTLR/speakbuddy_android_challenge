package com.amazingtlr.usecase.fact

import com.amazingtlr.api.FactRepository
import com.amazingtlr.api.model.FactListResponse
import com.amazingtlr.usecase.UseCaseResult
import com.amazingtlr.usecase.mapNetworkResultToUseCaseResult
import kotlinx.coroutines.flow.Flow

class FactListUseCase(private val factRepository: FactRepository) {
    operator fun invoke(neededPage: Int): Flow<UseCaseResult<FactListResponse, Any>> =
        factRepository.observeFacts(neededPage).mapNetworkResultToUseCaseResult()
}