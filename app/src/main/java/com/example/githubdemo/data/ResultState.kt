package com.example.githubdemo.data

import androidx.compose.ui.Modifier

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Failure(val error: Exception) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
}