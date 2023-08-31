package com.example.movietmdb.data.model.people


import com.google.gson.annotations.SerializedName

data class PeopleImages(
    @SerializedName("id")
    val id: Int,
    @SerializedName("profiles")
    val profiles: List<Profile>
)