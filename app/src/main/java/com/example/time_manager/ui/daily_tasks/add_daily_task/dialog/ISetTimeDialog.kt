package com.example.time_manager.ui.daily_tasks.add_daily_task.dialog

interface ISetTimeDialog {
    fun onDialogPositiveClick(startTime: Int, endTime: Int)
    fun onDialogNegativeClick()
}

