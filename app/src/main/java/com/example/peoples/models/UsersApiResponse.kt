package com.example.peoples.models

import com.google.gson.annotations.SerializedName

data class UsersApiResponse(
    val info: Info,
    @SerializedName("results")
    val userData: List<UserData>
)