package com.amazingtlr.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {
    @Query("SELECT * FROM facts")
    fun getAllFacts(): Flow<List<FactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(factEntities: List<FactEntity>)

    @Query("DELETE FROM facts")
    suspend fun clearAll()

    //A better solution would be to use a different table with a corresponding
    // entityId to store the seen facts
    @Query("UPDATE facts SET seen = 1 WHERE id = :factId")
    fun markFactAsSeen(factId: String)

    @Query("SELECT * FROM facts WHERE seen = 1")
    fun getAllSeenFacts(): Flow<List<FactEntity>>
}