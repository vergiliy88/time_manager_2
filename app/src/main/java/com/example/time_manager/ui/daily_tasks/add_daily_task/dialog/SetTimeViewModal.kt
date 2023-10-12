package com.example.time_manager.ui.daily_tasks.add_daily_task.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.time_manager.utils.Utils

class SetTimeViewModal: ViewModel() {
    val startTime = MutableLiveData<Int?>(null)
    val endTime = MutableLiveData<Int?>(null)


    fun setStartTime(startH: Int, startMin: Int){
        startTime.value = Utils.convertHoursToMinutes(startH, startMin)
    }

    fun setEndTime(endH: Int, endM: Int){
        endTime.value = Utils.convertHoursToMinutes(endH, endM)
    }

}