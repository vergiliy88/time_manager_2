package com.example.time_manager.domain.repositories

import com.example.time_manager.domain.models.DailyTask
import kotlinx.coroutines.flow.Flow


interface DailyTaskRepository {
    suspend fun getDailyTasks(): Flow<List<DailyTask>>
    suspend fun getDailyTaskById(taskId: Long): DailyTask
    suspend fun saveDailyTask(task: DailyTask): Long
    suspend fun updateDailyTask(task: DailyTask): Int
    suspend fun deleteDailyTask(task: DailyTask): Int
    suspend fun deleteDailyTaskById(taskId: Long): Int
}