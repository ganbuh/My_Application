package com.example.myapplication.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.CalculateBmiUseCase
import com.example.myapplication.domain.usecase.SaveBmiResultUseCase
import com.example.myapplication.presentation.events.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val calculateBmiUseCase: CalculateBmiUseCase,
    private val saveBmiResultUseCase: SaveBmiResultUseCase,
) : ViewModel() {

    private val _height = mutableStateOf("")
    val height: State<String> = _height

    private val _weight = mutableStateOf("")
    val weight: State<String> = _weight

    private val _bmiResult = mutableStateOf("")
    val bmiResult: State<String> = _bmiResult

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

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

    fun saveBmiResult() {
        val bmiValue = _bmiResult.value
        if (bmiValue.isNotBlank() && bmiValue != "入力が正しくありません") {
            viewModelScope.launch {
                val isSaved = saveBmiResultUseCase.execute(bmiValue)
                if (isSaved) {
                    sendSnackbarMessage("保存に成功しました")
                } else {
                    sendSnackbarMessage("保存できません：BMIが無効です")
                }
            }
        } else {
            viewModelScope.launch {
                sendSnackbarMessage("保存できません：BMIが無効です")
            }
        }
    }

    private suspend fun sendSnackbarMessage(message: String) {
        // UiEvent を送信するための非同期処理
        _uiEvent.send(UiEvent.ShowSnackbar(message))
    }
}
