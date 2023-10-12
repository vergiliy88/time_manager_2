package com.example.time_manager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.time_manager.data.database.dao.DailyTaskDAO
import com.example.time_manager.data.database.dao.TaskDAO
import com.example.time_manager.data.database.dao.UserDAO
import com.example.time_manager.data.database.models.DailyTaskDB
import com.example.time_manager.data.database.models.TaskDB
import com.example.time_manager.data.database.models.UserDB


@Database(entities = [
                        UserDB::class,
                        TaskDB::class,
                        DailyTaskDB::class
                     ], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun taskDao(): TaskDAO
    abstract fun dailyTaskDao(): DailyTaskDAO
}