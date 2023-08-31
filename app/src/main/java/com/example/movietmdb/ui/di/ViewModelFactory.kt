package com.example.movietmdb.ui.di

import com.example.movietmdb.domain.Repository
import com.example.movietmdb.ui.viewmodel.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactory {
    @Provides
    fun provideHomeViewModelFactory(repository: Repository) : HomeViewModelFactory {
        return HomeViewModelFactory(repository)
    }
}