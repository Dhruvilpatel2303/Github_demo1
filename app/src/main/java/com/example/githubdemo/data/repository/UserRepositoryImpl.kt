package com.example.githubdemo.data.repository

import com.example.githubdemo.data.ResultState
import com.example.githubdemo.data.model.User
import com.example.githubdemo.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) : UserRepository{
    override suspend fun saveUserProfile(name: String): ResultState<Unit> {
        val currentUser = firebaseAuth.currentUser

        val uid = currentUser?.uid ?: return ResultState.Failure(Exception("User not logged in"))
        val email = currentUser.email ?: ""
        val user = User(
            uid = uid,
            email = email,
            name = email
        )

        return try {
            firebaseDatabase.reference
                .child("users")
                .child(uid)
                .setValue(user)
                .await()
            ResultState.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            ResultState.Failure(e)
        }
    }

    override suspend fun getCurrentUserProfile(): ResultState<User> {
        val uid = firebaseAuth.currentUser?.uid ?: return ResultState.Failure(Exception("User not logged in"))

        return try {
            val snapshot = firebaseDatabase.reference
                .child("users")
                .child(uid)
                .get()
                .await()

            val user = snapshot.getValue(User::class.java)
                ?: return ResultState.Failure(Exception("User not found"))
            ResultState.Success(user)
        } catch (e: Exception) {
            ResultState.Failure(e)
        }
    }

    override fun getCurrentUserId(): String? {
        return  firebaseAuth.currentUser?.uid
    }

}