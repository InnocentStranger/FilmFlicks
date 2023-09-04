package com.example.pagingdemo.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movietmdb.data.api.ApiService
import com.example.movietmdb.data.paging.ResponsePagingHelper


class ContentPageSource(
    private val apiService: ApiService,
    private val apiKey : String,
    private val type : String,
    private val id  : Int? = null,
    private val query : String? = null
) : PagingSource<Int, Any>() {
    override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
    private suspend fun getApiResponse(position : Int) : ResponsePagingHelper {
        var pages : Int? = null
        var  data : List<Any>? = null
        when(type) {
            "search" -> {
                val response = apiService.getMultiSearch(apiKey,position,query!!)
                pages = response.body()?.totalPages
                data = response.body()?.results
            }
            "popular_movies" -> {
                val response = apiService.getPopularMovies(apiKey,position);
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "top_rated_movies" -> {
                val response = apiService.getTopRatedMovies(apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "upcoming_movies" -> {
                val response = apiService.getUpcomingMovies(apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "now_playing_movies" -> {
                val response = apiService.getNowPlayingMovies(apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "popular_tv_series" -> {
                val response = apiService.getPopularTvSeries(apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "top_rated_tv_series" -> {
                val response = apiService.getTopRatedTvSeries(apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "on_the_air_tv_series" -> {
                val response = apiService.getOnTheAirTvSeries(apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "airing_today_tv_series" -> {
                val response = apiService.getAiringTodayTvSeries(apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList;
            }
            "trending_people" -> {
                val response = apiService.getTrendingPeople(apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "popular_people" -> {
                val response = apiService.getPopularPeople(apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "similar_movies" -> {
                val response = apiService.getSimilarMovies(id!!,apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "similar_tv_series" -> {
                val response = apiService.getSimilarTvSeries(id!!,apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "recommended_movies" -> {
                val response = apiService.getRecommendedMovies(id!!,apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
            "recommended_tv_series" -> {
                val response = apiService.getRecommendedTvSeries(id!!,apiKey,position)
                pages = response.body()?.totalPages;
                data = response.body()?.itemList
            }
        }
        return ResponsePagingHelper(pages,data)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        return try{
            val position = params.key ?: 1
            val response = getApiResponse(position)
            if(response.response != null) {
                LoadResult.Page<Int, Any>(
                    data = response.response,
                    prevKey = if (position == 0) null else position - 1,
                    nextKey = if (position == response.pages) null else position + 1
                )
            }else {
                Log.i("MyTag",response.toString())
                return LoadResult.Error(throw Exception(response.toString()))
            }
        }catch (e : Exception) {
            Log.i("MyTag", "Page Source ${e.toString()}")
            LoadResult.Error(e)
        }
    }

}