package com.amazingtlr.retrofit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RetrofitFactsResponse(
    @SerialName("data") val facts: List<RetrofitFactResponse>,
    @SerialName("current_page") val currentPage: Int,
    @SerialName("last_page") val totalPages: Int
)
