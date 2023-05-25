package com.example.task.retrofit

import com.example.task.models.LoginRequest
import com.example.task.models.LoginResponse
import com.example.task.models.NotesResponse
import com.example.task.models.OtpLoginRequest
import com.example.task.models.OtpLoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("users/phone_number_login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("users/verify_otp")
    suspend fun otpLogin(@Body otpLoginRequest: OtpLoginRequest): OtpLoginResponse

    @GET("users/test_profile_list")
    suspend fun getNotes(@Header("Authorization") auth_token : String?): NotesResponse
}