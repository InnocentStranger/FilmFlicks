package com.example.movietmdb.data.model.people


import com.example.movietmdb.data.model.content.Content
import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("known_for")
    val knownFor: List<Content>,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("credit_id")
    val creditId: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("job")
    val job: String,
    @SerializedName("cast_id")
    val castId: Int,
    @SerializedName("character")
    val character: String,
    @SerializedName("order")
    val order: Int,
)