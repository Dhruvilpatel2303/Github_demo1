package com.example.githubdemo.domain.repository

import com.example.githubdemo.data.ResultState
import com.example.githubdemo.data.model.User

interface UserRepository {
    suspend fun saveUserProfile(name: String): ResultState<Unit>

    suspend fun getCurrentUserProfile(): ResultState<User>

    fun getCurrentUserId(): String?
}