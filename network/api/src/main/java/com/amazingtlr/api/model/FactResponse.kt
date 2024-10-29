package com.amazingtlr.api.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class FactResponse(
    val id: String = Uuid.random().toString(), //TODO : Create Uuui in a usecase model instead
    val fact: String,
    val length: Int
)