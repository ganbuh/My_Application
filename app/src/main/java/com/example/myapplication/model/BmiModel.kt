package com.example.myapplication.model

class BmiModel(
    private val heightCm: Float,
    private val weightKg: Float
) {
    fun calculateBmi(): Float {
        val heightM = heightCm / 100
        return weightKg / (heightM * heightM)
    }
}