package com.example.task.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("number") val number: String?,
)
