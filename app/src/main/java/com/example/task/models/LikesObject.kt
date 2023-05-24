package com.example.task.models

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class LikesObject(
    @SerializedName("profiles") var profiles: List<Profiles>,
    @SerializedName("can_see_profile") val canSeeProfiles: Boolean,
    @SerializedName("likes_received_count") val likesReceived: Int,
)

data class Profiles(
    @SerializedName("first_name") val firstName : String,
    @SerializedName("avatar") val avatar : String,
)
