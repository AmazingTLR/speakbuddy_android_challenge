package com.amazingtlr.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FactEntity::class], version = 1, exportSchema = false)
abstract class RoomFactDatabase : RoomDatabase() {
    abstract fun factDao(): FactDao

    companion object {
        @Volatile
        private var INSTANCE: RoomFactDatabase? = null

        fun getDatabase(context: Context): RoomFactDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomFactDatabase::class.java,
                    "fact_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}