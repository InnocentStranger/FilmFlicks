package com.example.movietmdb.data.model.people


import com.google.gson.annotations.SerializedName

data class DiscoverPeople(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val itemList : List<People>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)