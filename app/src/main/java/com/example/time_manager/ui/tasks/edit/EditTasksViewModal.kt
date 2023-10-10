package com.example.time_manager.ui.tasks.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.time_manager.data.repositories.TaskRepositoryImpl
import com.example.time_manager.domain.models.Task
import com.example.time_manager.domain.usecase.TasksUseCase
import com.example.time_manager.ui.base.BaseViewModal
import com.example.time_manager.utils.Utils
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class EditTasksViewModal  : BaseViewModal() {
    private var job: Job? = null

    val priorityList: Array<Int> = Utils.priority
    val hours: Array<Int> = Utils.hours
    val minutes: Array<Int> = Utils.minutes

    private var selectPriority = 0
    private var selectHour = 0
    private var selectMinutes = 0

    private val currentTask = Task()
    var taskIdToEdit = MutableLiveData<Long>(0)

    private val taskUseCase = TasksUseCase(TaskRepositoryImpl())

    var updateStatus = MutableLiveData<Boolean>(false)
    val taskFroDB = MutableLiveData<Task>(null)

    fun selectedPriority(index: Int) {
        selectPriority = priorityList[index]
    }
    fun selectedHour(index: Int) {
        selectHour = hours[index]
    }
    fun selectedMinutes(index: Int) {
        selectMinutes = minutes[index]
    }

    fun setTaskId(taskId: Long?){
        taskId?.let {
            taskIdToEdit.value = it
        }
    }

    fun saveTask(name: String){
        currentTask.name = name
        currentTask.expectedTime = Utils.convertHoursToMinutes(selectHour, selectMinutes)
        currentTask.priority = selectPriority
        if(taskIdToEdit.value != 0L) {
            currentTask.id = taskIdToEdit.value
            viewModelScope.launch {
                val result = taskUseCase.updateTask(currentTask)
                if (result > 0) {
                    updateStatus.value = true
                }
            }
        } else {
            viewModelScope.launch {
                val id = taskUseCase.saveTask(currentTask)
                if (id > 0) {
                    updateStatus.value = true
                }
            }
        }
    }

    fun loadTask() {
        job?.cancel()
        job = viewModelScope.launch {
            taskIdToEdit.value?.let {
                if (it > 0) {
                    taskFroDB.value = taskUseCase.getTaskById(it)
                }
            }
        }
    }

    fun delTask() {
        viewModelScope.launch {
            taskIdToEdit.value?.let {
                if (it > 0) {
                    val countDeleted = taskUseCase.delTaskById(it)
                    if (countDeleted > 0) {
                        updateStatus.value = true
                    }
                }
            }
        }
    }

}

