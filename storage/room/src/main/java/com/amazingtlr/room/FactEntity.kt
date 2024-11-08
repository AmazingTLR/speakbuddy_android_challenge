package com.amazingtlr.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amazingtlr.api.LocalFactResponse

@Entity(tableName = "facts")
data class FactEntity(
    @PrimaryKey val id: String,
    val fact: String,
    val length: Int,
    val seen: Int // 0 = unseen, 1 = seen - Boolean are not supported in ApiLevel < 30 by Room
)

fun LocalFactResponse.toFactEntity(): FactEntity = FactEntity(
    id = id,
    fact = fact,
    length = length,
    seen = 0
)

fun FactEntity.toLocalFactResponse(): LocalFactResponse = LocalFactResponse(id, fact, length)