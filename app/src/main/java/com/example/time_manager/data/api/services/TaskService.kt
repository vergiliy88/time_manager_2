package com.example.time_manager.data.api.services

import com.example.time_manager.data.api.models.response.FactsResponse
import retrofit2.Response
import retrofit2.http.GET

interface TaskService {
    @GET("/facts")
    suspend fun getFacts(): FactsResponse
}