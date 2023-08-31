package com.example.movietmdb.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movietmdb.R
import com.example.movietmdb.databinding.FragmentSeeAllBinding
import com.example.movietmdb.ui.adapter.ContentAdapterType1
import com.example.movietmdb.ui.adapter.ContentAdapterTypeAll
import com.example.movietmdb.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class SeeAllFragment : Fragment() {
    private lateinit var binding : FragmentSeeAllBinding
    private lateinit var adapter : ContentAdapterTypeAll
    private val viewModel : HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSeeAllBinding>(
            inflater,
            R.layout.fragment_see_all,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        var type = viewModel.allLiveData.value ?: ""
        Log.i("MyTag",type)
        getData(type)
    }

    private fun initRv() {
        adapter = ContentAdapterTypeAll()
        binding.allRv.adapter = adapter
        binding.allRv.layoutManager = GridLayoutManager(binding.root.context,2)
    }
    private fun getData(type : String) {
        when(type) {
            "popular_movies" -> {
                viewModel.getPopularMoviesPaging(type).observe(viewLifecycleOwner, Observer {
                    lifecycleScope.launch {
                        adapter.submitData(it)
                        adapter.notifyDataSetChanged()
                    }
                })
            }
            "top_rated_movies" -> {
                viewModel.getTopRatedMoviesPaging(type).observe(viewLifecycleOwner, Observer {
                    lifecycleScope.launch {
                        adapter.submitData(it)
                        adapter.notifyDataSetChanged()
                    }
                })
            }
            "now_playing_movies" -> {
                viewModel.getNowPlayingMoviesPaging(type).observe(viewLifecycleOwner, Observer {
                    lifecycleScope.launch {
                        adapter.submitData(it)
                        adapter.notifyDataSetChanged()
                    }
                })
            }
            "upcoming_movies" -> {
                viewModel.getUpcomingMoviesPaging(type).observe(viewLifecycleOwner, Observer {
                    lifecycleScope.launch {
                        adapter.submitData(it)
                        adapter.notifyDataSetChanged()
                    }
                })
            }
        }
    }
}