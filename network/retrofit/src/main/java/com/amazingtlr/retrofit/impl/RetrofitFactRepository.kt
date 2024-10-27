package com.amazingtlr.retrofit.impl

import com.amazingtlr.api.FactRepository
import com.amazingtlr.api.NetworkResult
import com.amazingtlr.api.model.FactListResponse
import com.amazingtlr.api.model.FactResponse
import com.amazingtlr.api.toNetworkSuccessOrError
import com.amazingtlr.retrofit.FactService
import com.amazingtlr.retrofit.getAndParse
import com.amazingtlr.retrofit.model.toFactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetrofitFactRepository(private val factService: FactService) : FactRepository {

    override fun observeFact(): Flow<NetworkResult<FactResponse>> {
        return flow {
            emit(
                factService.getFact().getAndParse().toNetworkSuccessOrError {
                    it.toFactResponse()
                }
            )
        }
    }

    override fun observeFacts(neededPage: Int): Flow<NetworkResult<FactListResponse>> {
        return flow {
            emit(
                factService.getFacts(neededPage).getAndParse().toNetworkSuccessOrError {
                    FactListResponse(
                        it.facts.map {
                            it.toFactResponse()
                        },
                        it.currentPage,
                        it.totalPages
                    )
                }
            )
        }
    }
}