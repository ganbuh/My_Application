package com.example.myapplication.domain.usecase

interface SaveBmiResultUseCase {
    suspend fun execute(bmiResult: String): Boolean
}