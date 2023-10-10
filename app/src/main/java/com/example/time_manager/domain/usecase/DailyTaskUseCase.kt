package com.example.time_manager.domain.usecase


import com.example.time_manager.domain.models.DailyTask
import com.example.time_manager.domain.repositories.DailyTaskRepository
import kotlinx.coroutines.flow.Flow

class DailyTaskUseCase(private val repository: DailyTaskRepository) {
    suspend fun getDailyTasks(): Flow<List<DailyTask>> {
        return repository.getDailyTasks()
    }

    suspend fun getDailyTaskById(taskId: Long): DailyTask {
        return repository.getDailyTaskById(taskId)
    }

    suspend fun saveDailyTask(dailyTask: DailyTask): Long {
        return repository.saveDailyTask(dailyTask)
    }

    suspend fun updateDailyTask(dailyTask: DailyTask): Int{
        return repository.updateDailyTask(dailyTask)
    }

    suspend fun repository(dailyTask: DailyTask): Int {
        return repository.deleteDailyTask(dailyTask)
    }

    suspend fun repository(taskId: Long): Int{
        return repository.deleteDailyTaskById(taskId)
    }
}