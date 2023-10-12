package com.example.time_manager.data.repositories

import com.example.time_manager.App
import com.example.time_manager.data.database.models.TaskDB
import com.example.time_manager.data.mappers.DailyTaskMapper
import com.example.time_manager.data.mappers.DailyTaskMapper.mapToDB
import com.example.time_manager.domain.models.DailyTask
import com.example.time_manager.domain.models.Task
import com.example.time_manager.domain.repositories.DailyTaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DailyTaskRepositoryImpl: DailyTaskRepository {
    private val dbDaily = App.instance.database.dailyTaskDao()
    private val dbTask = App.instance.database.taskDao()
    override suspend fun getDailyTasks(): Flow<List<DailyTask>> {
        val dailyTask = dbDaily.getDailyTaskFlow()
        val tasks: List<TaskDB> = dbTask.getTaskList()
//        return DailyTaskMapper.mapFromDBFlowList(dailyTask.map { item ->
//            item.forEach { _ ->
//                tasks = dbTask.getTaskList()
//            }
//        return@map item} , tasks)
        return DailyTaskMapper.mapFromDBFlowList(dailyTask, tasks)
    }

    override suspend fun getDailyTaskById(taskId: Long): DailyTask {
        TODO("Not yet implemented")
    }

    override suspend fun saveDailyTask(dailyTask: DailyTask): Long {
        return dbDaily.insertDailyTask(mapToDB(dailyTask))
    }

    override suspend fun updateDailyTask(dailyTask: DailyTask): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDailyTask(dailyTask: DailyTask): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDailyTaskById(taskId: Long): Int {
        TODO("Not yet implemented")
    }
}