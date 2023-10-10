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
        job?.cancel()
        job = viewModelScope.launch {
            val result = dailyTaskUseCase.getDailyTasks()
            result.collectLatest {
                _tasksOfDay.value = it
            }
        }
    }

    fun saveDailyTask() {

    }

    fun getApi() {
//        viewModelScope.launch {
//            val result = getTasks.getTask()
//            Log.d("API", result.currentPage.toString()?:"1111")
//        }

//            emit(Resource.loading(data = null))
//            try {
//                emit(Resource.message(data = getTasks.getTask()))
//            } catch (exception: Exception) {
//                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//            }
        Log.d("DATA TEST", "2323")
    }

//    fun getTasks() {
//        viewModelScope.launch {
//            val list = mutableListOf<DailyTask>()
//            list.add(0, DailyTask(1, "Завтрак", "12", 2))
//            list.add(1, DailyTask(2, "Путь на работу", "33", 2))
//            list.add(2, DailyTask(3, "Обед", "14", 2))
//            _tasksOfDay.value = list
//        }
//    }


}