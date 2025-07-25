package com.example.githubdemo.data.repository

import com.example.githubdemo.data.ResultState
import com.example.githubdemo.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : AuthRepository{
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(
        email: String,
        password: String
    ): ResultState<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()

            ResultState.Success(result.user!!)
        } catch (e: Exception) {
            ResultState.Failure(e)
        }
    }

    override suspend fun signup(
        name: String,
        email: String,
        password: String
    ): ResultState<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            result?.user?.updateProfile(
                UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build()
            )?.await()
            ResultState.Success(result.user!!)
        } catch (e: Exception) {
            ResultState.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}