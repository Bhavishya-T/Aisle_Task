package com.example.task.models

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class NotesResponse(
    @SerializedName("invites") val invites: Invites,
    @SerializedName("likes") val likes: LikesObject,
)

data class GeneralInformation (
    @SerializedName("first_name") val name : String,
    @SerializedName("age") val age : Int
)


data class InvitesProfiles (
    @SerializedName("general_information") val generalInformation : GeneralInformation,
    @SerializedName("photos") val photos : List<PhotoList>
)

data class PhotoList(
    @SerializedName("photo") val photo : String,
)

data class Invites(
    @SerializedName("profiles") val profiles : List<InvitesProfiles>,
    @SerializedName("totalPages") val totalPages : Int = 0,
    @SerializedName("pending_invitations_count") val invitations : Int = 0
)
