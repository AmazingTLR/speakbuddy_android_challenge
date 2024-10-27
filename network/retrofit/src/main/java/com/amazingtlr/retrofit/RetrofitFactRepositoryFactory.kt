package com.amazingtlr.retrofit

import com.amazingtlr.api.FactRepository
import com.amazingtlr.retrofit.impl.RetrofitFactRepository

object RetrofitFactRepositoryFactory {
    fun createFactRepository(retrofitFactService: FactService): FactRepository =
        RetrofitFactRepository(retrofitFactService)
}