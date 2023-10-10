package com.example.time_manager.data.database.dao

import androidx.room.*
import com.example.time_manager.data.database.models.UserDB
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert
    suspend fun insertUser(user: UserDB): Long

    @Query("SELECT * FROM UserDB")
    fun getUserFlow(): Flow<UserDB>

    @Delete
    suspend fun deleteUser(user: UserDB): Int

    @Query("DELETE FROM UserDB WHERE id = :userId;")
    suspend fun deleteById(userId: Long): Int

    @Update
    suspend fun updateUser(user: UserDB)
}