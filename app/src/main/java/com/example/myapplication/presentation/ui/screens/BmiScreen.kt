package com.example.myapplication.presentation.ui.screens

import com.example.myapplication.presentation.viewmodel.BmiViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BmiScreen(viewModel: BmiViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = viewModel.height.value,
            onValueChange = { viewModel.onHeightChange(it) },
            label = { Text("身長(cm)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.weight.value,
            onValueChange = { viewModel.onWeightChange(it) },
            label = { Text("体重(kg)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.calculateBmi() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("BMIを計算する")
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "BMI結果: ${viewModel.bmiResult.value}",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
