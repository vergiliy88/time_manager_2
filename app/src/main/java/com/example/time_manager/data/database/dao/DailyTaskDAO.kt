package com.example.time_manager.data.database.dao

import androidx.room.*
import com.example.time_manager.data.database.models.DailyTaskDB
import kotlinx.coroutines.flow.Flow

@Dao
interface  DailyTaskDAO {
    @Insert
    suspend fun insertDailyTask(user: DailyTaskDB): Long

    @Query("SELECT * FROM DailyTaskDB")
    fun getDailyTaskFlow(): Flow<List<DailyTaskDB>>

    @Query("SELECT * FROM TaskDB WHERE id = :dailyTaskId")
    suspend fun getDailyTaskById(dailyTaskId: Long): DailyTaskDB

    @Delete
    suspend fun deleteDailyTask(task: DailyTaskDB): Int

    @Query("DELETE FROM TaskDB WHERE id = :dailyTaskId;")
    suspend fun deleteDailyById(dailyTaskId: Long): Int

    @Update
    suspend fun updateDailyTask(dailyTask: DailyTaskDB): Int
}