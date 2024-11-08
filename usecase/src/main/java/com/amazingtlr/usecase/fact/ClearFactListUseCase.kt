package com.amazingtlr.usecase.fact

import com.amazingtlr.api.LocalFactRepository

class ClearFactListUseCase(private val localFactRepository: LocalFactRepository) {
    suspend operator fun invoke() {
        localFactRepository.clearAll()
    }
}