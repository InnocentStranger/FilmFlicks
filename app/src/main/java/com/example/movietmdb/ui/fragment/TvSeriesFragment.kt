package com.example.movietmdb.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietmdb.R
import com.example.movietmdb.databinding.FragmentTvSeriesBinding
import com.example.movietmdb.ui.adapter.ContentAdapterType1
import com.example.movietmdb.ui.adapter.ContentAdapterType2
import com.example.movietmdb.ui.adapter.ContentAdapterType3
import com.example.movietmdb.ui.viewmodel.HomeViewModel


class TvSeriesFragment : Fragment() {
    private lateinit var binding : FragmentTvSeriesBinding
    private val viewModel : HomeViewModel by activityViewModels()
    private lateinit var popularAdapter : ContentAdapterType1
    private lateinit var topRatedAdapter : ContentAdapterType2
    private lateinit var onTheAirAdapter : ContentAdapterType3
    private lateinit var airingTodayAdapter : ContentAdapterType1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tv_series, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        provideData()
        allClick()
    }

    private fun onClickContent(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_tvSeriesFragment_to_tvSeriesDetailsFragment,bundle)
    }

    private fun initRv() {
        popularAdapter = ContentAdapterType1(::onClickContent)
        binding.popularRv.adapter = popularAdapter
        binding.popularRv.layoutManager = LinearLayoutManager(binding.root.context,
            LinearLayoutManager.HORIZONTAL,false)
        topRatedAdapter = ContentAdapterType2(::onClickContent)
        binding.topRatedRv.adapter = topRatedAdapter
        binding.topRatedRv.layoutManager = LinearLayoutManager(binding.root.context,
            LinearLayoutManager.HORIZONTAL,false)
        airingTodayAdapter = ContentAdapterType1(::onClickContent)
        binding.upcomingRv.adapter = airingTodayAdapter
        binding.upcomingRv.layoutManager = LinearLayoutManager(binding.root.context,
            LinearLayoutManager.HORIZONTAL,false)
        onTheAirAdapter = ContentAdapterType3(::onClickContent)
        binding.nowPlayingRv.adapter = onTheAirAdapter
        binding.nowPlayingRv.layoutManager = GridLayoutManager(binding.root.context,3,
            GridLayoutManager.HORIZONTAL,false)
    }

    private fun provideData() {
        viewModel.getPopularTvSeriesFirstPage().observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful && it.body() != null) {
                popularAdapter.updateData(it.body()!!.itemList)
                popularAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getTopRatedTvSeriesFirstPage().observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful && it.body() != null) {
                topRatedAdapter.updateData(it.body()!!.itemList)
                topRatedAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getOnTheAirTvSeriesFirstPage().observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful && it.body() != null) {
                onTheAirAdapter.updateData(it.body()!!.itemList)
                onTheAirAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getAiringTodayTvSeriesFirstPage().observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful && it.body() != null) {
                airingTodayAdapter.updateData(it.body()!!.itemList)
                airingTodayAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun allClick() {
        binding.popularAll.setOnClickListener{
            val bundle  = Bundle()
            bundle.putString("type", "popular_tv_series")
            findNavController().navigate(R.id.action_tvSeriesFragment_to_seeAllFragment,bundle)
        }
        binding.topRatedAll.setOnClickListener {
            val bundle  = Bundle()
            bundle.putString("type", "top_rated_tv_series")
            findNavController().navigate(R.id.action_tvSeriesFragment_to_seeAllFragment,bundle)
        }
        binding.nowPlayingAll.setOnClickListener {
            val bundle  = Bundle()
            bundle.putString("type", "on_the_air_tv_series")
            findNavController().navigate(R.id.action_tvSeriesFragment_to_seeAllFragment,bundle)
        }
        binding.upComingAll.setOnClickListener {
            val bundle  = Bundle()
            bundle.putString("type", "airing_today_tv_series")
            findNavController().navigate(R.id.action_tvSeriesFragment_to_seeAllFragment,bundle)
        }
    }
}