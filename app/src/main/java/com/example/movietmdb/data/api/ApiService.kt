package com.example.movietmdb.data.api

import com.example.movietmdb.data.model.content.Content
import com.example.movietmdb.data.model.content.ContentCredits
import com.example.movietmdb.data.model.content.DiscoverContent
import com.example.movietmdb.data.model.people.DiscoverPeople
import com.example.movietmdb.data.model.people.People
import com.example.movietmdb.data.model.people.PeopleCredits
import com.example.movietmdb.data.model.people.PeopleImages
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/popular?")
    suspend fun getPopularMovies(@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    @GET("/3/movie/top_rated?")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    @GET("/3/movie/upcoming?")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    @GET("/3/movie/now_playing?")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    // -------------------------------------------------------------

    @GET("/3/tv/popular?")
    suspend fun getPopularTvSeries(@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    @GET("/3/tv/top_rated?")
    suspend fun getTopRatedTvSeries(@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    @GET("/3/tv/on_the_air?")
    suspend fun getOnTheAirTvSeries(@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    @GET("/3/tv/airing_today?")
    suspend fun getAiringTodayTvSeries(@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    // --------------------------------------

    @GET("/3/trending/person/day?")
    suspend fun getTrendingPeople(@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverPeople>

    @GET("/3/person/{person_id}/movie_credits?")
    suspend fun getPeopleMovies(@Path("person_id") personId : Int,@Query("api_key") apiKey : String) : Response<PeopleCredits>

    @GET("/3/person/{person_id}/tv_credits?")
    suspend fun getPeopleTvSeries(@Path("person_id") personId : Int,@Query("api_key") apiKey : String) : Response<PeopleCredits>

    @GET("/3/person/{person_id}/images?")
    suspend fun getPeopleImages(@Path("person_id") personId: Int,@Query("api_key") apiKey : String) : Response<PeopleImages>

    @GET("/3/person/popular?")
    suspend fun getPopularPeople(@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverPeople>

    // ------------------------------------------

    @GET("/3/movie/{movie_id}/credits?")
    suspend fun getMovieCast(@Path("movie_id") movieId: Int,@Query("api_key") apiKey : String) : Response<ContentCredits>

    @GET("/3/movie/{movie_id}?")
    suspend fun getMovieDetails(@Path("movie_id") movieId : Int,@Query("api_key") apiKey : String) : Response<Content>

    @GET("/3/person/{person_id}?")
    suspend fun getPeopleDetails(@Path("person_id") personId : Int, @Query("api_key") apiKey : String) : Response<People>

    @GET("/3/tv/{series_id}/credits?")
    suspend fun getTvSeriesCast(@Path("series_id") seriesId: Int,@Query("api_key") apiKey : String) : Response<ContentCredits>

    @GET("/3/movie/{movie_id}/similar?")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int,@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    @GET("/3/movie/{movie_id}/recommendations?")
    suspend fun getRecommendedMovies(@Path("movie_id") movieId: Int,@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    @GET("/3/tv/{series_id}/similar?")
    suspend fun getSimilarTvSeries(@Path("series_id") seriesId: Int,@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    @GET("/3/tv/{series_id}/recommendations?")
    suspend fun getRecommendedTvSeries(@Path("series_id") seriesId: Int,@Query("api_key") apiKey : String,@Query("page") page : Int) : Response<DiscoverContent>

    // --------------------------------------------------------------



}