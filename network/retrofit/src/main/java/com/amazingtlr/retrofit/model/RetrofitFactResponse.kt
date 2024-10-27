package com.amazingtlr.retrofit.model

import com.amazingtlr.api.model.FactResponse
import kotlinx.serialization.Serializable

@Serializable
data class RetrofitFactResponse(
    val fact: String,
    val length: Int
)

fun RetrofitFactResponse.toFactResponse(): FactResponse {
    return FactResponse(fact, length)
}