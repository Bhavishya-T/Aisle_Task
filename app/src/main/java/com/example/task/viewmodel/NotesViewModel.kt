package com.example.task.viewmodel

import androidx.lifecycle.ViewModel
import com.example.task.models.LikesObject
import com.example.task.models.NotesResponse
import com.example.task.models.Profiles

class NotesViewModel : ViewModel() {
    fun getData() : NotesResponse{
        var notesResponse : NotesResponse
        var profile1 : Profiles
        profile1 = Profiles("Ajith","https://testimages.aisle.co/dd510d5260eeebcdc7d7fc752c598c39728894004.png")
        var profile2 : Profiles
        profile2 = Profiles("Ishant","https://testimages.aisle.co/58b125e52d319c0390fc2d68b7da2ba6729804903.png")
        var likesObject : LikesObject
        likesObject = LikesObject(mutableListOf(profile1,profile2),false,2)
        notesResponse = NotesResponse(null,likesObject)
        return notesResponse
    }
}