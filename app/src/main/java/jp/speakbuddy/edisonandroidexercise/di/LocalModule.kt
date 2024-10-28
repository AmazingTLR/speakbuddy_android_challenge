package jp.speakbuddy.edisonandroidexercise.di

import android.content.Context
import com.amazingtlr.api.LocalFactRepository
import com.amazingtlr.room.FactDao
import com.amazingtlr.room.RoomFactDatabase
import com.amazingtlr.room.RoomLocalFactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun provideFactDao(@ApplicationContext appContext: Context): FactDao {
        return RoomFactDatabase.getDatabase(appContext).factDao()
    }

    @Provides
    @Singleton
    fun provideLocalFactRepository(factDao: FactDao): LocalFactRepository {
        return RoomLocalFactRepository(factDao)
    }
}