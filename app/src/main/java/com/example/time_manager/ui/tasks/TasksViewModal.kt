package com.example.time_manager.ui.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.time_manager.data.repositories.TaskRepositoryImpl
import com.example.time_manager.domain.models.Task
import com.example.time_manager.domain.usecase.TasksUseCase
import com.example.time_manager.ui.base.BaseViewModal
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TasksViewModal  : BaseViewModal() {
    var tasks: MutableLiveData<List<Task>> = MutableLiveData()
    private val taskUseCase = TasksUseCase(TaskRepositoryImpl())
    private var job: Job? = null

    init {
        job?.cancel()
        job = viewModelScope.launch {
            val result = taskUseCase.getTasks()
            result.collectLatest {
                tasks.value = it
            }
        }
    }
}