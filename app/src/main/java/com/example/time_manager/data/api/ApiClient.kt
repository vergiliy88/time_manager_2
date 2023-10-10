package com.example.time_manager.data.api

import com.example.time_manager.data.api.services.TaskService
import com.example.time_manager.data.api.services.UserServices
import com.example.time_manager.utils.Config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ApiClient {
    private fun getClient(baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    val apiUserModule: UserServices = getClient(Config.baseUrl).create(UserServices::class.java)
    val apiTasksModule: TaskService = getClient("https://catfact.ninja").create(TaskService::class.java)
}