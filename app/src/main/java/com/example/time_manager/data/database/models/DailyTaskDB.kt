package com.example.time_manager.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DailyTaskDB {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    var taskId: Long? = null
    var spendTime: Int? = null
//    var task: TaskDB? = null
    var date: Long? = null
}