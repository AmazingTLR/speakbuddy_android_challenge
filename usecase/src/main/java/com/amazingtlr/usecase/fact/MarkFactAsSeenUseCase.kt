package com.amazingtlr.usecase.fact

import com.amazingtlr.api.LocalFactRepository

class MarkFactAsSeenUseCase(
    private val localFactRepository: LocalFactRepository
) {
    suspend operator fun invoke(factId: String) {
        localFactRepository.markFactAsSeen(factId)
    }
}