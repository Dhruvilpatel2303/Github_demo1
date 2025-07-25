package com.example.githubdemo.domain.repository

import com.example.githubdemo.data.ResultState
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    
    val currentUser: FirebaseUser?
    
    suspend fun login(email: String, password: String): ResultState<FirebaseUser>
    
    suspend fun signup(name: String, email: String, password: String): ResultState<FirebaseUser>
    
    fun logout()
}