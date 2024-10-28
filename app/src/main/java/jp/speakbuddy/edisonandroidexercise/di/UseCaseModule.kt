package jp.speakbuddy.edisonandroidexercise.di

import com.amazingtlr.api.FactRepository
import com.amazingtlr.api.LocalFactRepository
import com.amazingtlr.usecase.fact.FactListUseCase
import com.amazingtlr.usecase.fact.FactUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideFactUseCase(factRepository: FactRepository): FactUseCase {
        return FactUseCase(factRepository)
    }

    @Provides
    @Singleton
    fun provideFactListUseCase(
        factRepository: FactRepository,
        localFactRepository: LocalFactRepository
    ): FactListUseCase {
        return FactListUseCase(
            factRepository = factRepository,
            localFactRepository = localFactRepository
        )
    }
}