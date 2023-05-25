package com.example.task.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.task.database.AppPreferences
import com.example.task.models.LikesObject
import com.example.task.models.NotesResponse
import com.example.task.models.Profiles
import com.example.task.retrofit.ApiInterface
import com.example.task.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesViewModel : ViewModel() {

    lateinit var notesResponse : NotesResponse
    fun getData() : NotesResponse{
        return notesResponse
    }

    suspend fun getLikesList() {
        val retrofit = RetrofitClient.buildService(ApiInterface::class.java)
        val res=AppPreferences.getSharedPreference().getString("token","")
        Log.d(ContentValues.TAG, res!!)
        notesResponse = retrofit.getNotes(res)
    }
}