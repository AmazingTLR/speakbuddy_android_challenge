package com.amazingtlr.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amazingtlr.api.LocalFactResponse
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(tableName = "facts")
data class FactEntity(
    @PrimaryKey val id: String,
    val fact: String,
    val length: Int,
)

@OptIn(ExperimentalUuidApi::class)
fun LocalFactResponse.toFact(): FactEntity = FactEntity(
    id = Uuid.random().toString(),
    fact = fact,
    length = length
)

fun FactEntity.toLocalFactResponse(): LocalFactResponse = LocalFactResponse(fact, length)