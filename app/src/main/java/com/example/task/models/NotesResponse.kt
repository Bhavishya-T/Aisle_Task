package com.example.task.models

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class NotesResponse(
    @SerializedName("invites") val invites: Objects?,
    @SerializedName("likes") val likes: LikesObject,
)

