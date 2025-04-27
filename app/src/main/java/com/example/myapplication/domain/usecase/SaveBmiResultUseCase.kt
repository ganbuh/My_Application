package com.example.myapplication.domain.usecase

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class SaveBmiResultUseCase @Inject constructor() {
    suspend fun execute(bmiResult: String, context: Context): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val content = "BMI: $bmiResult, Date: ${getCurrentDateTime()}"
                val file = File(context.filesDir, "bmi_result.txt")
                val fileOutputStream = FileOutputStream(file, true)
                fileOutputStream.bufferedWriter().use { writer ->
                    writer.appendLine(content)
                }
                println("BMI結果をファイルに保存しました: ${file.absolutePath}")
                true
            } catch (e: IOException) {
                e.printStackTrace()
                println("ファイルの保存に失敗しました: ${e.message}")
                false
            }
        }
    }

    private fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
        return sdf.format(Date())
    }
}