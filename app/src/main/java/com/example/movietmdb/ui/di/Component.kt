package com.example.movietmdb.ui.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,RepositoryModule::class,ViewModelFactory::class])
interface MovieComponent {
    fun movieSubComponent() : MovieSubComponent.Factory
}