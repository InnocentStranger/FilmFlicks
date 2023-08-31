package com.example.movietmdb.ui

import android.app.Application
import android.util.Log
import com.example.movietmdb.ui.di.DaggerMovieComponent
import com.example.movietmdb.ui.di.Injector
import com.example.movietmdb.ui.di.MovieComponent
import com.example.movietmdb.ui.di.MovieSubComponent
import dagger.Component

class App : Application(),Injector {
    private lateinit var movieComponent: MovieComponent
    override fun onCreate() {
        super.onCreate()
        movieComponent = DaggerMovieComponent.builder()
            .build()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return movieComponent.movieSubComponent().create()
    }
}