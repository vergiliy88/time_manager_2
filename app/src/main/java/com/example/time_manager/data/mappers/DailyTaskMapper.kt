package com.example.time_manager.data.mappers

import com.example.time_manager.data.database.models.DailyTaskDB
import com.example.time_manager.data.database.models.TaskDB
import com.example.time_manager.domain.models.DailyTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DailyTaskMapper {
    fun mapFromDB(dailyTaskDB: DailyTaskDB): DailyTask {
        val dailyTask = DailyTask()
        dailyTask.id = dailyTaskDB.id
        dailyTask.spendTime = dailyTaskDB.spendTime
//        if (dailyTaskDB.task !== null) {
//            dailyTask.task = TaskMapper.mapFromDB(dailyTaskDB.task!!)
//        } else {
//            dailyTask.task = null
//        }
        dailyTask.date = dailyTaskDB.date
        return dailyTask
    }

    fun mapToDB(dailyTask: DailyTask): DailyTaskDB {
        val dailyTaskDB = DailyTaskDB()
        dailyTaskDB.id = dailyTask.id
        dailyTaskDB.spendTime = dailyTask.spendTime
//        dailyTaskDB.task = TaskMapper.mapToDB(dailyTask.task!!)
        dailyTaskDB.date = dailyTask.date
        return dailyTaskDB
    }

    fun mapFromDBFlowList(dailyTaskDB: Flow<List<DailyTaskDB>>, tasksDB: List<TaskDB>): Flow<List<DailyTask>> {
        return dailyTaskDB.map { item ->
            val resultList: MutableList<DailyTask> = mutableListOf()
            for (dailyTaskDB in item) {
                val domainModel = DailyTask()
                domainModel.id = dailyTaskDB.id
                domainModel.spendTime = dailyTaskDB.spendTime
                val task = tasksDB.find { item -> item.id!! == dailyTaskDB.taskId }
                domainModel.task = TaskMapper.mapFromDB(task!!)
                domainModel.date = dailyTaskDB.date
                resultList.add(domainModel)
            }
            return@map resultList
        }
    }
}