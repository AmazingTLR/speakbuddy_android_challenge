package com.amazingtlr.usecase.fact

import com.amazingtlr.api.FactRepository
import com.amazingtlr.api.LocalFactRepository
import com.amazingtlr.api.LocalFactResponse
import com.amazingtlr.api.model.FactListResponse
import com.amazingtlr.api.model.FactResponse
import com.amazingtlr.usecase.UseCaseResult
import com.amazingtlr.usecase.mapNetworkResultToUseCaseResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
class FactListUseCase(
    private val factRepository: FactRepository,
    private val localFactRepository: LocalFactRepository
) {
    operator fun invoke(neededPage: Int): Flow<UseCaseResult<FactListResponse, Any>> {
        // Fetch facts from the network and store them in the local database
        // If the network failed, return the facts from the local database

        return factRepository.observeFacts(neededPage)
            .mapNetworkResultToUseCaseResult<FactListResponse, Any>()
            .flatMapLatest {
                when (val networkUseCaseResult = it) {
                    is UseCaseResult.Success -> {
                        localFactRepository.insertAll(networkUseCaseResult.value.factList.map { factResponse ->
                            LocalFactResponse(
                                id = factResponse.id,
                                fact = factResponse.fact,
                                length = factResponse.length
                            )
                        })
                        flowOf(networkUseCaseResult)
                    }

                    is UseCaseResult.Failure -> {
                        localFactRepository.observeFacts().map { localFactResponseList ->
                            UseCaseResult.Success(
                                FactListResponse(
                                    factList = localFactResponseList.map { localFactResponse ->
                                        FactResponse(
                                            fact = localFactResponse.fact,
                                            length = localFactResponse.length
                                        )
                                    },
                                    currentPage = 0,
                                    totalPages = 0
                                )
                            )
                        }
                    }
                }
            }
    }
}