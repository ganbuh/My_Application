package com.example.myapplication.domain.usecase

import com.example.myapplication.data.repository.FileBmiResultRepository
import javax.inject.Inject

class SaveBmiResultUseCaseImpl @Inject constructor(
    private val bmiResultRepository: FileBmiResultRepository
) : SaveBmiResultUseCase {
    override suspend fun execute(bmiResult: String): Boolean {
        return bmiResultRepository.saveBmiResult(bmiResult)
    }
}