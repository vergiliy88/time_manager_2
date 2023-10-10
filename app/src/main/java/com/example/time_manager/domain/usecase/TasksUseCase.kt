package com.example.time_manager.domain.usecase

import com.example.time_manager.domain.models.Task
import com.example.time_manager.domain.repositories.TasksRepository
import kotlinx.coroutines.flow.Flow

class TasksUseCase(private val taskRepository: TasksRepository) {
    suspend fun getTasks(): Flow<List<Task>> {
        return taskRepository.getTasks()
    }

    suspend fun getTaskById(taskId: Long): Task{
        return taskRepository.getTaskById(taskId)
    }

    suspend fun saveTask(task: Task): Long {
        return taskRepository.saveTask(task)
    }

    suspend fun updateTask(task: Task): Int{
        return taskRepository.updateTask(task)
    }

    suspend fun delTask(task: Task): Int {
        return taskRepository.deleteTask(task)
    }

    suspend fun delTaskById(taskId: Long): Int{
        return taskRepository.deleteTaskById(taskId)
    }
}