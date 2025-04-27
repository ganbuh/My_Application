package com.example.myapplication.presentation.events

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
}