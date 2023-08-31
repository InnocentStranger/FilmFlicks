package com.example.movietmdb.ui.di

import com.example.movietmdb.data.api.ApiService
import com.example.movietmdb.data.repository.RepositoryImp
import com.example.movietmdb.domain.Repository
import com.example.movietmdb.ui.util.Constants.API_KEY
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(apiService : ApiService) : Repository {
        return RepositoryImp(apiService,API_KEY)
    }
}

