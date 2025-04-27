package com.example.myapplication.domain.usecase


import com.example.myapplication.data.repository.FileBmiResultRepository
import javax.inject.Inject

class SaveBmiResultUseCase @Inject constructor(
    private val bmiResultRepository: FileBmiResultRepository
) {
    suspend fun execute(bmiResult: String): Boolean {
        return bmiResultRepository.saveBmiResult(bmiResult)
    }
}