package com.example.time_manager.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.time_manager.ui.main.MainActivity
import com.example.time_manager.databinding.SplashScreenBinding


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding: SplashScreenBinding
    private lateinit var _viewModal: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModal = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            SplashScreenViewModel::class.java
        )
        _viewModal.startTimer()
        _viewModal.canRedirectScreen.observe(this){
            it?.let {
                if (it) {
                    val i = Intent(this, MainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(i)
                    finish()
                }
            }
        }
    }
}