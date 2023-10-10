package com.example.time_manager.data.api.models.response

import com.google.gson.annotations.SerializedName

data class FactsResponse(
    @SerializedName("current_page")
    val currentPage: Int? = null,
    val data: List<FactResponse>? = null,
)