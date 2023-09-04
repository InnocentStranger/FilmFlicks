package com.example.movietmdb.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.movietmdb.data.api.ApiService
import com.example.movietmdb.data.model.content.Content
import com.example.movietmdb.data.model.content.ContentCredits
import com.example.movietmdb.data.model.content.DiscoverContent
import com.example.movietmdb.data.model.people.DiscoverPeople
import com.example.movietmdb.data.model.people.People
import com.example.movietmdb.data.model.people.PeopleCredits
import com.example.movietmdb.data.model.people.PeopleImages
import com.example.movietmdb.data.model.search.Result
import com.example.movietmdb.domain.Repository
import com.example.pagingdemo.paging.ContentPageSource
import retrofit2.Response

class RepositoryImp(private val apiService: ApiService, private val apiKey : String)
    : Repository {
    override fun getMoviesPaging(type: String,id : Int?): LiveData<PagingData<Content>> = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 60),
        pagingSourceFactory = { ContentPageSource(apiService,apiKey,type,id) }
    ).liveData as LiveData<PagingData<Content>>

    override fun getTvSeriesPaging(type: String,id : Int?): LiveData<PagingData<Content>> = Pager(
    config = PagingConfig(pageSize = 20, maxSize = 60),
    pagingSourceFactory = { ContentPageSource(apiService,apiKey,type,id) }
    ).liveData as LiveData<PagingData<Content>>

    override fun getPeoplePaging(type: String,id : Int?): LiveData<PagingData<People>> = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 60),
        pagingSourceFactory = { ContentPageSource(apiService,apiKey,type,id) }
    ).liveData as  LiveData<PagingData<People>>


    override suspend fun getContentFirstPage(type: String,id : Int?): Response<DiscoverContent> {
        return when(type) {
            "popular_movies" -> {
                apiService.getPopularMovies(apiKey,1)
            }
            "top_rated_movies" -> {
                apiService.getTopRatedMovies(apiKey,1)
            }
            "upcoming_movies" -> {
                apiService.getUpcomingMovies(apiKey,1)
            }
            "now_playing_movies" -> {
                apiService.getNowPlayingMovies(apiKey,1)
            }
            "popular_tv_series" -> {
                apiService.getPopularTvSeries(apiKey,1)
            }
            "top_rated_tv_series" -> {
                apiService.getTopRatedTvSeries(apiKey,1)
            }
            "on_the_air_tv_series" -> {
                apiService.getOnTheAirTvSeries(apiKey,1)
            }
            "airing_today_tv_series" -> {
                apiService.getAiringTodayTvSeries(apiKey,1)
            }
            "similar_movies" -> {
                apiService.getSimilarMovies(id!!,apiKey,1)
            }
            "similar_tv_series" -> {
                apiService.getSimilarTvSeries(id!!,apiKey,1)
            }
            "recommended_movies" -> {
                apiService.getRecommendedMovies(id!!,apiKey,1)
            }
            "recommended_tv_series" -> {
                apiService.getRecommendedTvSeries(id!!,apiKey,1)
            }
            else -> apiService.getPopularMovies(apiKey,1)
        }
    }

    override suspend fun getPeopleFirstPage(type: String): Response<DiscoverPeople> {
        return when(type) {
            "trending_people" -> {
                apiService.getTrendingPeople(apiKey, 1)
            }
            "popular_people" -> {
                apiService.getPopularPeople(apiKey, 1)
            }
            else ->  apiService.getTrendingPeople(apiKey, 1)
        }
    }

    override suspend fun getPeopleMovies(id: Int): Response<PeopleCredits> {
        return apiService.getPeopleMovies(id,apiKey)
    }

    override suspend fun getPeopleTvSeries(id : Int): Response<PeopleCredits> {
        return apiService.getPeopleTvSeries(id,apiKey)
    }

    override suspend fun getPeopleImages(id: Int): Response<PeopleImages> {
        return apiService.getPeopleImages(id,apiKey)
    }

    override suspend fun getMovieCast(id : Int): Response<ContentCredits> {
        return apiService.getMovieCast(id,apiKey)
    }

    override suspend fun getTvSeriesCast(id: Int): Response<ContentCredits> {
        return apiService.getTvSeriesCast(id,apiKey)
    }
    override suspend fun getMovieDetails(id : Int) : Response<Content> {
        return apiService.getMovieDetails(id,apiKey)
    }

    override suspend fun getTvSeriesDetails(id: Int): Response<Content> {
        return apiService.getTvSeriesDetails(id,apiKey)
    }

    override suspend fun getPeopleDetails(id: Int): Response<People> {
        return apiService.getPeopleDetails(id,apiKey)
    }

    override fun getSearchResults(query: String): LiveData<PagingData<Result>> = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 60),
        pagingSourceFactory = { ContentPageSource(apiService,apiKey,"search",null,query) }
    ).liveData as LiveData<PagingData<Result>>
}