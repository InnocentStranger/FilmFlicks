package com.example.movietmdb.ui.di

import com.example.movietmdb.ui.home.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelFactory::class])
interface MovieSubComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Builder
    interface Factory {
        fun create(): MovieSubComponent
    }
}