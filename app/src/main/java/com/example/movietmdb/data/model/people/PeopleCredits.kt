package com.example.movietmdb.data.model.people


import com.example.movietmdb.data.model.content.Content
import com.google.gson.annotations.SerializedName

data class PeopleCredits(
    @SerializedName("cast")
    val cast: List<Content>,
    @SerializedName("id")
    val id: Int
)