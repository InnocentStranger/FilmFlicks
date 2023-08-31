package com.example.movietmdb.data.model.content

import com.example.movietmdb.data.model.people.People
import com.google.gson.annotations.SerializedName

data class ContentCredits(
    @SerializedName("cast")
    val cast: List<People>,
    @SerializedName("id")
    val id: Int
)