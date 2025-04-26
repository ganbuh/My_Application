package com.example.myapplication.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.myapplication.domain.usecase.CalculateBmiUseCase

class BmiViewModel : ViewModel() {

    private val calculateBmiUseCase = CalculateBmiUseCase()

    private val _height = mutableStateOf("")
    val height: State<String> = _height

    private val _weight = mutableStateOf("")
    val weight: State<String> = _weight

    private val _bmiResult = mutableStateOf("")
    val bmiResult: State<String> = _bmiResult

    fun onHeightChange(newHeight: String) {
        _height.value = newHeight
    }

    fun onWeightChange(newWeight: String) {
        _weight.value = newWeight
    }

    @SuppressLint("DefaultLocale")
    fun calculateBmi() {
        val heightCm = _height.value.toFloatOrNull()
        val weightKg = _weight.value.toFloatOrNull()

        if (heightCm != null && weightKg != null && heightCm > 0) {
            val bmi = calculateBmiUseCase.execute(heightCm, weightKg)
            _bmiResult.value = String.format("%.2f", bmi)
        } else {
            _bmiResult.value = "入力が正しくありません"
        }
    }
}
