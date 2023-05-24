package com.example.task.models

import com.google.gson.annotations.SerializedName

data class OtpLoginRequest(@SerializedName("number") val number: String?, @SerializedName("otp") val otp: String?,)
