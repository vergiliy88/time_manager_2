package com.example.time_manager.domain.usecase

import com.example.time_manager.domain.models.User
import com.example.time_manager.domain.repositories.UserRepository

class UserUseCase(private val userRep: UserRepository) {
    suspend fun getUser(): User {

        return userRep.getUser()
    }

    suspend fun saveUser(user: User): Long {
        return userRep.saveUser(user)
    }

    suspend fun updateUser(user: User) {
        userRep.updateUser(user)
    }

    suspend fun delUser(user: User) {
        userRep.delUser(user)
    }

    suspend fun authUser(login: String, pass: String) {
        userRep.authUser(login, pass)
    }
}