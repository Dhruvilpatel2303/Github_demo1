package com.example.githubdemo.ui.theme.viewmodel

import com.example.githubdemo.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) {
    
}