package com.example.time_manager.data.mappers

import com.example.time_manager.data.database.models.TaskDB
import com.example.time_manager.domain.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object TaskMapper  {
    fun mapFromDB(taskDB: TaskDB): Task {
        val task = Task()
        task.id = taskDB.id
        task.name = taskDB.name
        task.expectedTime = taskDB.expectedTime
        task.priority = taskDB.priority
        return task
    }

    fun mapToDB(task: Task): TaskDB {
        val taskDB = TaskDB()
        taskDB.id = task.id
        taskDB.name = task.name
        taskDB.expectedTime = task.expectedTime
        taskDB.priority = task.priority
        return taskDB
    }

    fun mapFromDBFlowList(taskDB: Flow<List<TaskDB>>): Flow<List<Task>> {
        return taskDB.map { item ->
            val resultList: MutableList<Task> = mutableListOf()
            for (taskDB in item) {
                val domainModel = Task()
                domainModel.id = taskDB.id
                domainModel.name = taskDB.name
                domainModel.expectedTime = taskDB.expectedTime
                domainModel.priority = taskDB.priority
                resultList.add(domainModel)
            }
            return@map resultList
        }
    }

    fun mapFromDBList(tasksDB: List<TaskDB>): List<Task>{
        val taskList: MutableList<Task> = mutableListOf()
        tasksDB.forEach {
            val task = Task()
            task.id = it.id
            task.name = it.name
            task.expectedTime = it.expectedTime
            task.priority = it.priority
        }
        return taskList.toList()
    }

}