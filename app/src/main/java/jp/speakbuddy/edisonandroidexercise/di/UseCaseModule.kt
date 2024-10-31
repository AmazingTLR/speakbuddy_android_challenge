package jp.speakbuddy.edisonandroidexercise.di

import com.amazingtlr.api.FactRepository
import com.amazingtlr.api.LocalFactRepository
import com.amazingtlr.usecase.fact.ClearFactListUseCase
import com.amazingtlr.usecase.fact.FactListUseCase
import com.amazingtlr.usecase.fact.HistoryFactListUseCase
import com.amazingtlr.usecase.fact.MarkFactAsSeenUseCase
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
    fun provideFactListUseCase(
        factRepository: FactRepository,
        localFactRepository: LocalFactRepository
    ): FactListUseCase {
        return FactListUseCase(
            factRepository = factRepository,
            localFactRepository = localFactRepository
        )
    }

    @Provides
    @Singleton
    fun provideClearFactListUseCase(localFactRepository: LocalFactRepository): ClearFactListUseCase {
        return ClearFactListUseCase(localFactRepository)
    }

    @Provides
    @Singleton
    fun provideMarkFactAsSeenUseCase(localFactRepository: LocalFactRepository): MarkFactAsSeenUseCase {
        return MarkFactAsSeenUseCase(localFactRepository)
    }

    @Provides
    @Singleton
    fun provideSeenFactListUseCase(localFactRepository: LocalFactRepository): HistoryFactListUseCase {
        return HistoryFactListUseCase(localFactRepository)
    }
}