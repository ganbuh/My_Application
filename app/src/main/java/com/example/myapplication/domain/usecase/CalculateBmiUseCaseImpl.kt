package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Bmi
import javax.inject.Inject

class CalculateBmiUseCaseImpl @Inject constructor() :CalculateBmiUseCase {
    override fun execute(heightCm: Float, weightKg: Float): Float {
        val bmi = Bmi(heightCm, weightKg)
        return bmi.calculate()
    }
}