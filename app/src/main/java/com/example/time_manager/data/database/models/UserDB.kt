package com.example.time_manager.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserDB   {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    var name: String? = null
    var email: String? = null
    var avatar: String? = null
    var userSubscriptionCount: Int? = null
    var token: String? = null
    var refreshToken: String? = null
}

