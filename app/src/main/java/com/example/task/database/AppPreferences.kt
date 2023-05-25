package com.example.task.database

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

object AppPreferences {
    private lateinit var sharedPreferences: SharedPreferences

    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences("Aisle", MODE_PRIVATE)
    }

    fun getSharedPreference() : SharedPreferences{
        return sharedPreferences
    }
}