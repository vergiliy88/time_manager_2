package com.example.time_manager.domain.repositories

import com.example.time_manager.domain.models.User

interface UserRepository {
    suspend fun getUser(): User
    suspend fun saveUser(user: User): Long
    suspend fun updateUser(user: User)
    suspend fun delUser(user: User)
    suspend fun deleteById(userId: Long): Int
    suspend fun authUser(login: String, pass: String): User
}