package com.example.task.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Objects

data class OtpLoginResponse(
    @field:SerializedName("token")
    val token: String? = null)

