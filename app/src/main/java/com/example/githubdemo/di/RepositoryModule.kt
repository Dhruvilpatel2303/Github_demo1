package com.example.githubdemo.di

import com.example.githubdemo.data.repository.AuthRepositoryImpl
import com.example.githubdemo.data.repository.UserRepositoryImpl
import com.example.githubdemo.domain.repository.AuthRepository
import com.example.githubdemo.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Singleton
    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
    
    @Singleton
    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}