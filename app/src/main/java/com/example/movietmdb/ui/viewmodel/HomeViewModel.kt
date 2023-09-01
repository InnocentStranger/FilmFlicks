package com.example.movietmdb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movietmdb.domain.Repository


class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun getPopularMoviesFirstPage() = liveData{
        emit(repository.getContentFirstPage("popular_movies",null))
    }
    fun getTopRatedMoviesFirstPage() = liveData {
        emit(repository.getContentFirstPage("top_rated_movies",null))
    }
    fun getNowPlayingMoviesFirstPage() = liveData {
        emit(repository.getContentFirstPage("now_playing_movies",null))
    }
    fun getUpcomingMoviesFirstPage() = liveData {
        emit(repository.getContentFirstPage("upcoming_movies",null))
    }
    fun getPopularMoviesPaging(type : String) = repository.getMoviesPaging(type,null).cachedIn(viewModelScope)

    fun getTopRatedMoviesPaging(type : String) = repository.getMoviesPaging(type,null).cachedIn(viewModelScope)
    fun getNowPlayingMoviesPaging(type : String) = repository.getMoviesPaging(type,null).cachedIn(viewModelScope)
    fun getUpcomingMoviesPaging(type : String) = repository.getMoviesPaging(type,null).cachedIn(viewModelScope)

    fun getMovieDetails(id : Int) = liveData {
        emit(repository.getMovieDetails(id))
    }

    fun getMovieCast(id: Int) = liveData {
        emit(repository.getMovieCast(id))
    }

    fun getSimilarMovieFirstPage(id : Int) = liveData {
        emit(repository.getContentFirstPage("similar_movies",id))
    }

    fun getRecommendedMovieFirstPage(id : Int) = liveData {
        emit(repository.getContentFirstPage("recommended_movies",id))
    }

    fun getSimilarMoviePaging(type : String,id : Int?) = repository.getMoviesPaging(type,id).cachedIn(viewModelScope)
    fun getRecommendedMoviePaging(type : String, id : Int?) = repository.getMoviesPaging(type,id).cachedIn(viewModelScope)

    fun getSimilarTvSeriesPaging(type : String, id: Int?) = repository.getTvSeriesPaging(type,id).cachedIn(viewModelScope)
    fun getRecommendedTvSeriesPaging(type : String, id: Int?) = repository.getTvSeriesPaging(type,id).cachedIn(viewModelScope)
    fun getPeopleDetails(id : Int) = liveData {
        emit(repository.getPeopleDetails(id))
    }
    fun getPeopleMovies(id : Int) = liveData {
        emit(repository.getPeopleMovies(id))
    }

    fun getPeopleTvSeries(id : Int) = liveData {
        emit(repository.getPeopleTvSeries(id))
    }

    fun getPopularTvSeriesPaging(type : String, id : Int?) = repository
        .getTvSeriesPaging(type,id).cachedIn(viewModelScope)

    fun getTopRatedTvSeriesPaging(type : String,id : Int?) = repository
        .getTvSeriesPaging(type,id).cachedIn(viewModelScope)

    fun getOnTheAirTvSeriesPaging(type : String,id : Int?) = repository
        .getTvSeriesPaging(type,id).cachedIn(viewModelScope)

    fun getAiringTodayTvSeiresPaging(type : String,id : Int?) = repository
        .getTvSeriesPaging(type,id).cachedIn(viewModelScope)

    fun getTvSeriesDetails(id : Int)  = liveData {
        emit(repository.getTvSeriesDetails(id))
    }

    fun getSimilarTvSeriesFirstPage(id : Int) = liveData {
        emit(repository.getContentFirstPage("similar_tv_series",id))
    }
    fun getRecommendedTvSeriesFirstPage(id : Int) = liveData {
        emit(repository.getContentFirstPage("recommended_tv_series",id))
    }
    fun getTvSeriesCast(id: Int) = liveData {
        emit(repository.getTvSeriesCast(id))
    }
    fun getPopularTvSeriesFirstPage() = liveData{
        emit(repository.getContentFirstPage("popular_tv_series",null))
    }

    fun getTopRatedTvSeriesFirstPage() = liveData{
        emit(repository.getContentFirstPage("top_rated_tv_series",null))
    }
    fun getOnTheAirTvSeriesFirstPage() = liveData{
        emit(repository.getContentFirstPage("on_the_air_tv_series",null))
    }
    fun getAiringTodayTvSeriesFirstPage() = liveData{
        emit(repository.getContentFirstPage("airing_today_tv_series",null))
    }
    fun getPopularPeopleFirstPage() = liveData {
        emit(repository.getPeopleFirstPage("popular_people"))
    }
    fun getTrendingPeopleFirstPage() = liveData {
        emit(repository.getPeopleFirstPage("trending_people"))
    }
    fun getPopularPeoplePaging(type : String,id : Int?) = repository.getPeoplePaging(type,id).cachedIn(viewModelScope)
    fun getTrendingPeoplePaging(type : String,id : Int?) = repository.getPeoplePaging(type,id).cachedIn(viewModelScope)
}