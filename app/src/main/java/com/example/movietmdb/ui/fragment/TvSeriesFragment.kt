package com.example.movietmdb.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietmdb.R
import com.example.movietmdb.databinding.FragmentMoviesBinding
import com.example.movietmdb.ui.adapter.ContentAdapterType1
import com.example.movietmdb.ui.adapter.ContentAdapterType2
import com.example.movietmdb.ui.adapter.ContentAdapterType3
import com.example.movietmdb.ui.viewmodel.HomeViewModel


class TvSeriesFragment : Fragment() {
//    private lateinit var binding : FragmentMoviesBinding
//    private val viewModel : HomeViewModel by activityViewModels()
//    private lateinit var popularAdapter : ContentAdapterType1
//    private lateinit var topRatedAdapter : ContentAdapterType2
//    private lateinit var nowPlayingAdapter : ContentAdapterType3
//    private lateinit var upcomingAdapter : ContentAdapterType1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_series, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initRv()
//        provideData()
//        allClick()
//    }
//
//    private fun initRv() {
//        popularAdapter = ContentAdapterType1()
//        binding.popularRv.adapter = popularAdapter
//        binding.popularRv.layoutManager = LinearLayoutManager(binding.root.context,
//            LinearLayoutManager.HORIZONTAL,false)
//        topRatedAdapter = ContentAdapterType2()
//        binding.topRatedRv.adapter = topRatedAdapter
//        binding.topRatedRv.layoutManager = LinearLayoutManager(binding.root.context,
//            LinearLayoutManager.HORIZONTAL,false)
//        upcomingAdapter = ContentAdapterType1()
//        binding.upcomingRv.adapter = upcomingAdapter
//        binding.upcomingRv.layoutManager = LinearLayoutManager(binding.root.context,
//            LinearLayoutManager.HORIZONTAL,false)
//        nowPlayingAdapter = ContentAdapterType3()
//        binding.nowPlayingRv.adapter = nowPlayingAdapter
//        binding.nowPlayingRv.layoutManager = GridLayoutManager(binding.root.context,3,
//            GridLayoutManager.HORIZONTAL,false)
//    }
//
//    private fun provideData() {
//        viewModel.getPopularMoviesFirstPage().observe(viewLifecycleOwner, Observer {
//            if(it.isSuccessful && it.body() != null) {
//                popularAdapter.updateData(it.body()!!.itemList)
//                popularAdapter.notifyDataSetChanged()
//            }
//        })
//        viewModel.getTopRatedMoviesFirstPage().observe(viewLifecycleOwner, Observer {
//            if(it.isSuccessful && it.body() != null) {
//                topRatedAdapter.updateData(it.body()!!.itemList)
//                topRatedAdapter.notifyDataSetChanged()
//            }
//        })
//        viewModel.getNowPlayingMoviesFirstPage().observe(viewLifecycleOwner, Observer {
//            if(it.isSuccessful && it.body() != null) {
//                nowPlayingAdapter.updateData(it.body()!!.itemList)
//                nowPlayingAdapter.notifyDataSetChanged()
//            }
//        })
//        viewModel.getUpcomingMoviesFirstPage().observe(viewLifecycleOwner, Observer {
//            if(it.isSuccessful && it.body() != null) {
//                upcomingAdapter.updateData(it.body()!!.itemList)
//                upcomingAdapter.notifyDataSetChanged()
//            }
//        })
//    }
//
//    private fun allClick() {
//        binding.popularAll.setOnClickListener{
//            viewModel.updateAllType("popular_movies")
//
//        }
//        binding.topRatedAll.setOnClickListener {
//            viewModel.updateAllType("top_rated_movies")
//
//        }
//        binding.nowPlayingAll.setOnClickListener {
//            viewModel.updateAllType("now_playing_movies")
//
//        }
//        binding.upComingAll.setOnClickListener {
//            viewModel.updateAllType("upcoming_movies")
//
//        }
//    }
}