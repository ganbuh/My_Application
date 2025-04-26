package com.example.myapplication.domain.model

data class Bmi(
    val heightCm: Float,
    val weightKg: Float
) {
    fun calculate(): Float {
        val heightM = heightCm / 100
        return weightKg / (heightM * heightM)
    }
}