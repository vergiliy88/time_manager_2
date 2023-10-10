package com.example.time_manager.data.api.services

import com.example.time_manager.data.api.models.request.SignIn
import com.example.time_manager.data.api.models.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserServices {
    @GET("")
    suspend fun getUser(): Response<UserResponse>

    @POST("/api/auth/sign-in")
    suspend fun authUser(@Body json: SignIn): Response<UserResponse>
}