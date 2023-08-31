package com.example.movietmdb.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movietmdb.data.model.content.DiscoverContent
import com.example.movietmdb.domain.Repository
import retrofit2.Response

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val allMutableLiveData = MutableLiveData<String>("")
    val allLiveData : LiveData<String>
        get() = allMutableLiveData

    private val contentIdMutableLiveData = MutableLiveData<Int>(0)
    val contentIdLiveData : LiveData<Int>
        get() = contentIdMutableLiveData

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

    fun updateAllType(type : String) {
        allMutableLiveData.value = type
    }
    fun updateContentId(id : Int) {
        contentIdMutableLiveData.value = id
    }
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

}