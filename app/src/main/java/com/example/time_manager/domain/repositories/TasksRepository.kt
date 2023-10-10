package com.example.time_manager.domain.repositories

import com.example.time_manager.domain.models.Task
import kotlinx.coroutines.flow.Flow


interface TasksRepository {
    suspend fun getTasks(): Flow<List<Task>>
    suspend fun getTaskById(taskId: Long): Task
    suspend fun saveTask(task: Task): Long
    suspend fun updateTask(task: Task): Int
    suspend fun deleteTask(task: Task): Int
    suspend fun deleteTaskById(taskId: Long): Int
}