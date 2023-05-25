package com.example.task.viewmodel
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.NotesActivity
import com.example.task.database.AppPreferences
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

    suspend fun login() : Boolean{
        val retrofit = RetrofitClient.buildService(ApiInterface::class.java)
        var loginResponse = retrofit.login(LoginRequest(phoneNumber.value))
        return true
    }

    fun setOtp(otp: String) {
        this.otp.value = otp
    }

    suspend fun loginWithOtp(): Boolean {
        val retrofit = RetrofitClient.buildService(ApiInterface::class.java)
        var res = retrofit.otpLogin(OtpLoginRequest(phoneNumber.value, otp.value))
        AppPreferences.getSharedPreference().edit().putString("token", res.token).apply()
        Log.d(ContentValues.TAG, "Token $res")
        return true
    }
}
