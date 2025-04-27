package com.example.myapplication.presentation.ui.screens

import android.util.Log
import com.example.myapplication.presentation.viewmodel.BmiViewModel
import com.example.myapplication.presentation.events.UiEvent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BmiScreen(viewModel: BmiViewModel = hiltViewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }

    // イベントを受け取る
    LaunchedEffect(Unit) {
        try {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.ShowSnackbar -> {
                        snackbarHostState.showSnackbar(event.message)
                    }
                }
            }
        }  catch (e: Exception) {
            Log.e("BmiScreen", "Error collection events: $(e.message)")
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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

            Button(
                onClick = { viewModel.saveBmiResult() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("結果を保存")
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "BMI結果: ${viewModel.bmiResult.value}",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
