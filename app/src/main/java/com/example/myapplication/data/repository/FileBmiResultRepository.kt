package com.example.myapplication.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class FileBmiResultRepository @Inject constructor(
    @ApplicationContext  val context: Context
) {
    suspend fun saveBmiResult(bmiResult: String): Boolean {
        return try {
                val content = "BMI: $bmiResult, Date: ${getCurrentDateTime()}"
                val file = File(context.filesDir, "bmi_result.txt")
                val fileOutputStream =
                    withContext(Dispatchers.IO) {
                        FileOutputStream(file, true)
                    }
                fileOutputStream.bufferedWriter().use { writer ->
                    writer.appendLine(content)
                }
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
    }

    private fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
        return sdf.format(Date())
    }
}