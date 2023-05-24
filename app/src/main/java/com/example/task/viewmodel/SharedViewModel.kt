package com.example.task.viewmodel
import android.content.ContentValues
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.NotesActivity
import com.example.task.models.LoginRequest
import com.example.task.models.LoginResponse
import com.example.task.models.NotesResponse
import com.example.task.models.OtpLoginRequest
import com.example.task.models.OtpLoginResponse
import com.example.task.retrofit.ApiInterface
import com.example.task.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedViewModel : ViewModel() {

    var phoneNumber = MutableLiveData<String>()
    val otp = MutableLiveData<String>()

    fun setData(data: String) {
        phoneNumber.value=data
    }

    fun login() : Boolean{
        val retrofit = RetrofitClient.buildService(ApiInterface::class.java)
        retrofit.login(LoginRequest(phoneNumber.value)).enqueue(
            object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(ContentValues.TAG, "Error "+ t.message)
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    Log.d(ContentValues.TAG, "Success "+response.toString())
                    Log.d(ContentValues.TAG, "Success "+response.body())
                }
            }
        )
        return true
    }

    fun setOtp(otp: String) {
        this.otp.value = otp
    }

    fun loginWithOtp() : Boolean{
        val retrofit = RetrofitClient.buildService(ApiInterface::class.java)
        retrofit.otpLogin(OtpLoginRequest(phoneNumber.value,otp.value)).enqueue(
            object : Callback<OtpLoginResponse> {
                override fun onFailure(call: Call<OtpLoginResponse>, t: Throwable) {
                    Log.d(ContentValues.TAG, "Error "+ t.message)
                }

                override fun onResponse(call: Call<OtpLoginResponse>, response: Response<OtpLoginResponse>) {
                    Log.d(ContentValues.TAG, "Success "+response.toString())
                    Log.d(ContentValues.TAG, "Success "+response.body())
                    var res= response.headers().get("auth_token").toString()
                    retrofit.getNotes(res).enqueue(
                        object : Callback<NotesResponse> {
                            override fun onFailure(call: Call<NotesResponse>, t: Throwable) {
                                Log.d(ContentValues.TAG, "Error "+ t.message)
                            }

                            override fun onResponse(call: Call<NotesResponse>, response: Response<NotesResponse>) {
                                Log.d(ContentValues.TAG, "Success Notes "+response.toString())
                                Log.d(ContentValues.TAG, "Success Notes"+response.body())
                            }
                        }
                    )
                }
            }
        )
        return true
    }
}
