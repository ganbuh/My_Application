package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.repository.FileBmiResultRepository
import com.example.myapplication.domain.repository.BmiResultRepository
import com.example.myapplication.domain.usecase.CalculateBmiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.myapplication.domain.usecase.CalculateBmiUseCaseImpl
import com.example.myapplication.domain.usecase.SaveBmiResultUseCase
import com.example.myapplication.domain.usecase.SaveBmiResultUseCaseImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCalculateBmiUseCase(): CalculateBmiUseCase {
        return CalculateBmiUseCaseImpl()
    }

    @Provides
    @Singleton
    fun provideSaveBmiResultUseCase(fileBmiResultRepository: FileBmiResultRepository): SaveBmiResultUseCase {
        return SaveBmiResultUseCaseImpl(fileBmiResultRepository)
    }

    @Provides
    @Singleton
    fun provideFileBmiResultRepository(@ApplicationContext context: Context): BmiResultRepository {
        return FileBmiResultRepository(context)
    }
}