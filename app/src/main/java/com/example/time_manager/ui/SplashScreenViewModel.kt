package com.example.time_manager.ui

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.time_manager.utils.Config

class SplashScreenViewModel: ViewModel() {

    val _canRedirectScreen = MutableLiveData<Boolean?>().apply { value = false }

    fun startTimer() {
        object : CountDownTimer(Config.preloadTimerStart, Config.preloadTimerStep) {
            override fun onTick(millisUntilFinished: Long) { }
            override fun onFinish() {
                _canRedirectScreen.value = true
            }
        }.start()
    }

    val canRedirectScreen: LiveData<Boolean?> = _canRedirectScreen
}