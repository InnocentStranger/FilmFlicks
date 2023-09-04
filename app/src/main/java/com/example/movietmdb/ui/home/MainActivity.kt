package com.example.movietmdb.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movietmdb.R
import com.example.movietmdb.databinding.ActivityMainBinding
import com.example.movietmdb.domain.Repository
import com.example.movietmdb.ui.di.Injector
import com.example.movietmdb.ui.fragment.MoviesFragment
import com.example.movietmdb.ui.fragment.TvSeriesFragment
import com.example.movietmdb.ui.viewmodel.HomeViewModel
import com.example.movietmdb.ui.viewmodel.HomeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (application as Injector).createMovieSubComponent()
            .inject(this)
        viewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]

        // navBar
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView = binding.bottomNavigationView
        navView.setupWithNavController(navController)

        /* To make bottomNavItem selection after going to other fragment, then going to some other item of bottomNavBar
        and then returning back */
        navView.setOnItemSelectedListener {
            item ->
            NavigationUI.onNavDestinationSelected(item, navController)
            return@setOnItemSelectedListener true
        }
    }
}