package jp.speakbuddy.edisonandroidexercise.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.ui.FactUITransformer
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UITransformerModule {
    @Provides
    @Singleton
    fun provideFactUITransformer(): FactUITransformer {
        return FactUITransformer()
    }
}