package com.example.time_manager.ui.daily_tasks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.time_manager.data.repositories.DailyTaskRepositoryImpl
import com.example.time_manager.domain.models.DailyTask
import com.example.time_manager.domain.usecase.DailyTaskUseCase
import com.example.time_manager.ui.base.BaseViewModal
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DailyViewModal : BaseViewModal() {
    var _tasksOfDay = MutableLiveData<List<DailyTask>>()
    private val dailyTaskUseCase = DailyTaskUseCase(DailyTaskRepositoryImpl())
    private var job: Job? = null

    init {
        loadData()
    }

    private fun loadData() {
        job?.cancel()
        job = viewModelScope.launch {
            val result = dailyTaskUseCase.getDailyTasks()
            result.collectLatest {
                _tasksOfDay.value = it
            }
        }
    }

    fun getApi() {
        Log.d("DATA TEST", "2323")
    }


}