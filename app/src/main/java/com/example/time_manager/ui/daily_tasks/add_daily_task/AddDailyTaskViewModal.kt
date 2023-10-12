package com.example.time_manager.ui.daily_tasks.add_daily_task

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.time_manager.data.repositories.DailyTaskRepositoryImpl
import com.example.time_manager.data.repositories.TaskRepositoryImpl
import com.example.time_manager.domain.models.DailyTask
import com.example.time_manager.domain.models.Task
import com.example.time_manager.domain.usecase.DailyTaskUseCase
import com.example.time_manager.domain.usecase.TasksUseCase
import com.example.time_manager.ui.base.BaseViewModal
import com.example.time_manager.utils.Utils.Companion.calcDiff
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddDailyTaskViewModal  : BaseViewModal() {
    var tasks: MutableLiveData<List<Task>> = MutableLiveData()
    private val taskUseCase = TasksUseCase(TaskRepositoryImpl())
    private val dailyTaskUseCase = DailyTaskUseCase(DailyTaskRepositoryImpl())
    private var job: Job? = null
    private var task: Task? = null
    private var dailyTask: DailyTask = DailyTask()
    private var timerIsStart = MutableLiveData(0)
    val expendTime = MutableLiveData<Int?>(0)
    var message = MutableLiveData<STATUS>()
    var lastInsertTask = MutableLiveData<Long>(null)

    init {
        job?.cancel()
        job = viewModelScope.launch {
            val result = taskUseCase.getTasks()
            result.collectLatest {
                tasks.value = it
            }
        }
    }

    fun setTask(position: Int) {
        task = tasks.value?.get(position)
    }

    fun setManualTime(startTime: Int, endTime: Int) {
        expendTime.value = calcDiff(startTime, endTime)
    }

    fun toggleTimer(status: Int){
        timerIsStart.value = status
    }

    @SuppressLint("SimpleDateFormat")
    fun saveDailyTask() {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())

        dailyTask.spendTime = expendTime.value
        dailyTask.taskId = task?.id
        dailyTask.task = task
        dailyTask.date = currentDate
        dailyTask.timerIsOn = timerIsStart.value?:0

        if (dailyTask.task == null) {
            message.value = STATUS.ERROR_TASK
            return
        }

        if (dailyTask.timerIsOn == 0 && dailyTask.spendTime == 0) {
            message.value = STATUS.ERROR_TIMER
            return
        }

        viewModelScope.launch {
            lastInsertTask.value = dailyTaskUseCase.saveDailyTask(dailyTask)
        }
    }

    enum class STATUS{

        ERROR_TASK, ERROR_TIMER
    }

}