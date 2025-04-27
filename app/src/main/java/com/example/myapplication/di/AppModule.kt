package com.example.myapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.myapplication.domain.usecase.CalculateBmiUseCase
import com.example.myapplication.domain.usecase.SaveBmiResultUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCalculateBmiUseCase(): CalculateBmiUseCase {
        return CalculateBmiUseCase()
    }

    @Provides
    @Singleton
    fun provideSaveBmiResultUseCase(): SaveBmiResultUseCase {
        return SaveBmiResultUseCase()
    }
}