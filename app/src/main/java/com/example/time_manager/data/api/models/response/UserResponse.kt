package com.example.time_manager.data.api.models.response

data class UserResponse (
    val name: String? = null,
    val email: String? = null,
    val isVerifyEmail: String? = null,
    val lastOnlineAt: String? = null,
    val isOnline: Int? = 0,
    val sex: Int? = 0,
    val birthday: String? = null,
    val avatar: String? = null,
    val userSubscriptionCount: Int? = null,
    val token: Token? = null
)