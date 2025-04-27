package com.example.myapplication.domain.usecase

interface CalculateBmiUseCase {
    fun execute(heightCm: Float, weightKg: Float): Float
}