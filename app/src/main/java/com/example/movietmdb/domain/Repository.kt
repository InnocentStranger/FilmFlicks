package com.example.movietmdb.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.movietmdb.data.model.content.Content
import com.example.movietmdb.data.model.content.ContentCredits
import com.example.movietmdb.data.model.content.DiscoverContent
import com.example.movietmdb.data.model.people.DiscoverPeople
import com.example.movietmdb.data.model.people.People
import com.example.movietmdb.data.model.people.PeopleCredits
import com.example.movietmdb.data.model.people.PeopleImages
import retrofit2.Response

interface Repository {
    fun getMoviesPaging(type: String,id : Int?) : LiveData<PagingData<Content>>
    fun getTvSeriesPaging(type : String,id : Int?) : LiveData<PagingData<Content>>
    fun getPeoplePaging(type : String,id : Int?) : LiveData<PagingData<People>>

    suspend fun getPeopleMovies(id : Int) : Response<PeopleCredits>
    suspend fun getPeopleTvSeries(id : Int) : Response<PeopleCredits>
    suspend fun getPeopleImages(id : Int) : Response<PeopleImages>
    suspend fun getMovieCast(id : Int) : Response<ContentCredits>
    suspend fun getTvSeriesCast(id : Int) : Response<ContentCredits>
    suspend fun getContentFirstPage(type : String,id: Int?) : Response<DiscoverContent>
    suspend fun getPeopleFirstPage(type : String) : Response<DiscoverPeople>
    suspend fun getMovieDetails(id : Int) : Response<Content>

}