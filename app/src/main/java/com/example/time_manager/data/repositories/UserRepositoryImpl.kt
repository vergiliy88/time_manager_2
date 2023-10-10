package com.example.time_manager.data.repositories

import com.example.time_manager.App
import com.example.time_manager.data.api.ApiClient.apiUserModule
import com.example.time_manager.data.api.models.request.SignIn
import com.example.time_manager.domain.models.User
import com.example.time_manager.domain.repositories.UserRepository

class UserRepositoryImpl: UserRepository {
    private val _db = App.getInstance().database.userDao()

    override suspend fun getUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: User): Long {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun delUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteById(userId: Long): Int {
        TODO("Not yet implemented")
    }

    override suspend fun authUser(login: String, pass: String): User {
        val auth = SignIn(login, pass, true)
        val user = apiUserModule.authUser(auth)
        TODO("Not yet implemented")
    }
}