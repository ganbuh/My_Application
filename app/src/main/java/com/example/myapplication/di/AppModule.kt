package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.repository.FileBmiResultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.myapplication.domain.usecase.CalculateBmiUseCase
import com.example.myapplication.domain.usecase.SaveBmiResultUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideSaveBmiResultUseCase(fileBmiResultRepository: FileBmiResultRepository): SaveBmiResultUseCase {
        return SaveBmiResultUseCase(fileBmiResultRepository)
    }

    @Provides
    @Singleton
    fun provideFileBmiResultRepository(@ApplicationContext context: Context): FileBmiResultRepository {
        return FileBmiResultRepository(context)
    }
}