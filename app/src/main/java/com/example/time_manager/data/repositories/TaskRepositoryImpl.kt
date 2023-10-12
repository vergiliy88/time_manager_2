package com.example.time_manager.data.repositories

import com.example.time_manager.App
import com.example.time_manager.data.mappers.TaskMapper
import com.example.time_manager.data.mappers.TaskMapper.mapFromDBFlowList
import com.example.time_manager.data.mappers.TaskMapper.mapFromDBList
import com.example.time_manager.domain.models.Task
import com.example.time_manager.domain.repositories.TasksRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl: TasksRepository {
//    private val taskApi = ApiClient.apiTasksModule
    private val db = App.instance.database.taskDao()
    override suspend fun getTasks(): Flow<List<Task>> {
//        val result = taskApi.getFacts()
        return mapFromDBFlowList(db.getTaskFlow())
    }

    override suspend fun getTaskById(taskId: Long): Task {
        return TaskMapper.mapFromDB(db.getTaskById(taskId))
    }

    override suspend fun saveTask(task: Task): Long {
        val taskDB = TaskMapper.mapToDB(task)
        return db.insertTask(taskDB)
    }

    override suspend fun updateTask(task: Task): Int {
        val taskDB = TaskMapper.mapToDB(task)
        return db.updateTask(taskDB)
    }

    override suspend fun deleteTask(task: Task): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTaskById(taskId: Long): Int {
        return db.deleteById(taskId)
    }

    override suspend fun getTaskList(): List<Task> {
        return mapFromDBList(db.getTaskList())
    }
}