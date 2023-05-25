package com.example.task.models

import com.google.gson.annotations.SerializedName

data class OtpLoginRequest(@field:SerializedName("number") val number: String?=null,
                           @field:SerializedName("otp") val otp: String?=null)
