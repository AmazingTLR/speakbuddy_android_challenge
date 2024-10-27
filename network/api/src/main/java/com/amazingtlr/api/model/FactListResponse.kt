package com.amazingtlr.api.model

data class FactListResponse(
    val factList: List<FactResponse>,
    val currentPage: Int,
    val totalPages: Int
)
