package com.example.time_manager.data.database.dao

import androidx.room.*
import com.example.time_manager.data.database.models.TaskDB
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDAO {
    @Insert
    suspend fun insertTask(user: TaskDB): Long

    @Query("SELECT * FROM TaskDB")
    fun getTaskFlow(): Flow<List<TaskDB>>

    @Query("SELECT * FROM TaskDB")
    suspend fun getTaskList(): List<TaskDB>

    @Query("SELECT * FROM TaskDB WHERE id = :taskId")
    suspend fun getTaskById(taskId: Long): TaskDB

    @Delete
    suspend fun deleteTask(task: TaskDB): Int

    @Query("DELETE FROM TaskDB WHERE id = :taskId;")
    suspend fun deleteById(taskId: Long): Int

    @Update
    suspend fun updateTask(user: TaskDB): Int
}