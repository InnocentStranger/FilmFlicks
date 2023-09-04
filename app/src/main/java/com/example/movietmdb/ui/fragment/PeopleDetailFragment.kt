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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.databinding.FragmentPeopleDetailBinding
import com.example.movietmdb.ui.adapter.ContentAdapterType1
import com.example.movietmdb.ui.viewmodel.HomeViewModel

class PeopleDetailFragment : Fragment() {
    private lateinit var binding : FragmentPeopleDetailBinding
    private lateinit var movieAdapter : ContentAdapterType1
    private lateinit var tvSeriesAdapter : ContentAdapterType1
    private val viewModel : HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_people_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = this.arguments?.getString("id")?.toInt()
        initRv()
        getData(id!!)
        // button
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        onAllClick(id)
    }
    private fun onAllClick(id : Int) {
        binding.movieAll.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type","artist_movies")
            bundle.putString("id",id.toString())
            findNavController().navigate(R.id.action_peopleDetailFragment_to_seeAllFragment,bundle)
        }
        binding.tvSeriesAll.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type","artist_tv_series")
            bundle.putString("id",id.toString())
            findNavController().navigate(R.id.action_peopleDetailFragment_to_seeAllFragment,bundle)
        }
    }
    private fun initRv(){
        movieAdapter = ContentAdapterType1(::onClickMovie)
        binding.rvMovies.adapter = movieAdapter
        binding.rvMovies.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)

        tvSeriesAdapter = ContentAdapterType1(::onClickTvSeries)
        binding.rvTvSeries.adapter = tvSeriesAdapter
        binding.rvTvSeries.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun onClickMovie(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_peopleDetailFragment_to_movieDetailFragment,bundle)
    }

    private fun onClickTvSeries(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_peopleDetailFragment_to_tvSeriesDetailsFragment,bundle)
    }


    private fun getData(id : Int) {
        viewModel.getPeopleDetails(id).observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful && it.body() != null) {
                val uri = "https://image.tmdb.org/t/p/w500/" + it.body()!!.profilePath
                Glide.with(binding.image.context)
                    .load(uri)
                    .into(binding.image)
                binding.name.text = it.body()!!.name
                binding.knownFor.text = it.body()!!.knownForDepartment
                binding.birthPlace.text = it.body()!!.placeOfBirth
                binding.dob.text = it.body()!!.birthday
                binding.description.text = it.body()!!.biography
            }
        })

       viewModel.getPeopleMovies(id).observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful && it.body() != null) {
                movieAdapter.updateData(it.body()!!.cast)
                movieAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getPeopleTvSeries(id).observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful && it.body() != null) {
                tvSeriesAdapter.updateData(it.body()!!.cast)
                tvSeriesAdapter.notifyDataSetChanged()
            }
        })
    }
}