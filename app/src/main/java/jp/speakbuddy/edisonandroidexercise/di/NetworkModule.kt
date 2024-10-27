package jp.speakbuddy.edisonandroidexercise.di

import com.amazingtlr.api.FactRepository
import com.amazingtlr.retrofit.FactService
import com.amazingtlr.retrofit.FactServiceProvider
import com.amazingtlr.retrofit.RetrofitFactRepositoryFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideFactService(): FactService {
        return FactServiceProvider.provide()
    }

    @Provides
    @Singleton
    fun provideFactRepository(factService: FactService): FactRepository {
        return RetrofitFactRepositoryFactory.createFactRepository(factService)
    }
}