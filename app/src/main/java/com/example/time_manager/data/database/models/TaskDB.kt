package com.example.time_manager.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TaskDB {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    var name: String? = null
    var expectedTime: Int? = null
    var priority: Int? = null
}