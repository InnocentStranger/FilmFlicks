package com.example.movietmdb.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.movietmdb.R
import com.example.movietmdb.databinding.ActivityMainBinding
import com.example.movietmdb.domain.Repository
import com.example.movietmdb.ui.di.Injector
import com.example.movietmdb.ui.fragment.MoviesFragment
import com.example.movietmdb.ui.fragment.TvSeriesFragment
import com.example.movietmdb.ui.viewmodel.HomeViewModel
import com.example.movietmdb.ui.viewmodel.HomeViewModelFactory
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

        var currId = R.id.movieNavBtn
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.movieNavBtn -> {
                    try {
                        if(currId != it.itemId) {replaceFragment(MoviesFragment())}
                        currId = it.itemId
                    }catch (e : Exception) {
                        Log.i("MyTag",e.toString())
                    }

                }
                R.id.tvSeriesNavBtn -> {
                    if(currId != it.itemId) {replaceFragment(TvSeriesFragment())}
                    currId = it.itemId
                }
            }
            true
        }
    }

    private fun AppCompatActivity.replaceFragment(fragment: Fragment) {
            val manager: FragmentManager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, fragment)
            transaction.commit()
    }
}