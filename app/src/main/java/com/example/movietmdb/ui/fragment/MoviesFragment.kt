package com.example.movietmdb.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietmdb.R
import com.example.movietmdb.databinding.FragmentMoviesBinding
import com.example.movietmdb.ui.adapter.ContentAdapterType1
import com.example.movietmdb.ui.adapter.ContentAdapterType2
import com.example.movietmdb.ui.adapter.ContentAdapterType3
import com.example.movietmdb.ui.viewmodel.HomeViewModel


class MoviesFragment() : Fragment() {
        private lateinit var binding : FragmentMoviesBinding
        private val viewModel : HomeViewModel by activityViewModels()
        private lateinit var popularAdapter : ContentAdapterType1
        private lateinit var topRatedAdapter : ContentAdapterType2
        private lateinit var nowPlayingAdapter : ContentAdapterType3
        private lateinit var upcomingAdapter : ContentAdapterType1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movies,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        provideData()
        allClick()
    }

    private fun onClickContent(id : Int) {
        try {
            val bundle  = Bundle()
            bundle.putString("id", id.toString())
            findNavController().navigate(R.id.action_moviesFragment_to_movieDetailFragment,bundle)
        }catch (e : Exception) {
            Log.i("MyTag",e.toString())
        }
    }

    private fun initRv() {
        popularAdapter = ContentAdapterType1(::onClickContent)
        binding.popularRv.adapter = popularAdapter
        binding.popularRv.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
        topRatedAdapter = ContentAdapterType2(::onClickContent)
        binding.topRatedRv.adapter = topRatedAdapter
        binding.topRatedRv.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
        upcomingAdapter = ContentAdapterType1(::onClickContent)
        binding.upcomingRv.adapter = upcomingAdapter
        binding.upcomingRv.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
        nowPlayingAdapter = ContentAdapterType3(::onClickContent)
        binding.nowPlayingRv.adapter = nowPlayingAdapter
        binding.nowPlayingRv.layoutManager = GridLayoutManager(binding.root.context,3,GridLayoutManager.HORIZONTAL,false)
    }

    private fun provideData() {
        viewModel.getPopularMoviesFirstPage().observe(viewLifecycleOwner, Observer {
                if(it.isSuccessful && it.body() != null) {
                    popularAdapter.updateData(it.body()!!.itemList)
                    popularAdapter.notifyDataSetChanged()
                }
            })
        viewModel.getTopRatedMoviesFirstPage().observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful && it.body() != null) {
                topRatedAdapter.updateData(it.body()!!.itemList)
                topRatedAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getNowPlayingMoviesFirstPage().observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful && it.body() != null) {
                nowPlayingAdapter.updateData(it.body()!!.itemList)
                nowPlayingAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getUpcomingMoviesFirstPage().observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful && it.body() != null) {
                upcomingAdapter.updateData(it.body()!!.itemList)
                upcomingAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun allClick() {
        binding.popularAll.setOnClickListener{
            val bundle  = Bundle()
            bundle.putString("type", "popular_movies")
            findNavController().navigate(R.id.action_moviesFragment_to_seeAllFragment,bundle)
        }
        binding.topRatedAll.setOnClickListener {
            val bundle  = Bundle()
            bundle.putString("type", "top_rated_movies")
            findNavController().navigate(R.id.action_moviesFragment_to_seeAllFragment,bundle)
        }
        binding.nowPlayingAll.setOnClickListener {
            val bundle  = Bundle()
            bundle.putString("type", "now_playing_movies")
            findNavController().navigate(R.id.action_moviesFragment_to_seeAllFragment,bundle)
        }
        binding.upComingAll.setOnClickListener {
            val bundle  = Bundle()
            bundle.putString("type", "upcoming_movies")
            findNavController().navigate(R.id.action_moviesFragment_to_seeAllFragment,bundle)
        }
    }
}