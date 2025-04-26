package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Bmi

class CalculateBmiUseCase {
    fun execute(heightCm: Float, weightKg: Float): Float {
        val bmi = Bmi(heightCm, weightKg)
        return bmi.calculate()
    }
}