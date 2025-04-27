package com.example.myapplication.domain.repository

interface BmiResultRepository {
    suspend fun saveBmiResult(bmiResult: String) : Boolean
}