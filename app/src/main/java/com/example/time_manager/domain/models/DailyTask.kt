package com.example.time_manager.domain.models



class DailyTask {
    var id: Long? = null
    var taskId: Long? = null
    var spendTime: Int? = null
    var task: Task? = null
    var timerIsOn: Int = 0
    var date: String? = null
}