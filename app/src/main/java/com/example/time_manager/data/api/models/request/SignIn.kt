package com.example.time_manager.data.api.models.request

data class SignIn (
    val email: String,
    val password: String,
    val remember: Boolean
)