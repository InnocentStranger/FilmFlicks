package com.example.movietmdb.data.model.content


import com.google.gson.annotations.SerializedName

data class DiscoverContent(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val itemList : List<Content>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)