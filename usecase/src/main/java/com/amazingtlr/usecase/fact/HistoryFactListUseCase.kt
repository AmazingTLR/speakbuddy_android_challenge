package com.amazingtlr.usecase.fact

import com.amazingtlr.api.LocalFactRepository
import com.amazingtlr.api.model.FactResponse
import com.amazingtlr.usecase.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class HistoryFactListUseCase(private val localFactRepository: LocalFactRepository) {
    operator fun invoke(): Flow<UseCaseResult<List<FactResponse>, Any>> {
        return try {
            localFactRepository.observeSeenFacts().map {
                UseCaseResult.Success(it.map { factResponse ->
                    FactResponse(
                        fact = factResponse.fact,
                        length = factResponse.length
                    )
                })
            }
        } catch (e: Exception) {
            flowOf(UseCaseResult.Failure(e))
        }
    }
}