package com.example.movietmdb.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movietmdb.R
import com.example.movietmdb.databinding.FragmentSeeAllBinding
import com.example.movietmdb.ui.adapter.SearchAdapterTypeAll
import com.example.movietmdb.ui.adapter.SearchAdapterType1
import com.example.movietmdb.ui.adapter.SearchPeopleAdapterType1
import com.example.movietmdb.ui.adapter.SearchPeopleAdapterTypeAll
import com.example.movietmdb.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class SeeAllFragment : Fragment() {
    private lateinit var binding : FragmentSeeAllBinding
    private lateinit var movieAdapter : SearchAdapterTypeAll
    private lateinit var tvSeriesAdapter : SearchAdapterTypeAll
    private lateinit var movieAdapterFirst : SearchAdapterType1
    private lateinit var tvSeriesAdapterFirst : SearchAdapterType1
    private lateinit var peopleAdapterFirst : SearchPeopleAdapterType1
    private lateinit var peopleAdapter : SearchPeopleAdapterTypeAll

    private val viewModel : HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
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
        val type = this.arguments?.getString("type") ?: ""
        val id = this.arguments?.getString("id")?.toInt()
        getData(type,id)
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun onClickPeople(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_seeAllFragment_to_peopleDetailFragment,bundle)
    }
    private fun onClickMovie(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_seeAllFragment_to_movieDetailFragment,bundle)
    }
    private fun onClickTvSeries(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_seeAllFragment_to_tvSeriesDetailsFragment,bundle)
    }
    private fun initRv() {
        peopleAdapter = SearchPeopleAdapterTypeAll(::onClickPeople)
        tvSeriesAdapterFirst = SearchAdapterType1(::onClickTvSeries)
        tvSeriesAdapter = SearchAdapterTypeAll(::onClickTvSeries)
        movieAdapterFirst = SearchAdapterType1(::onClickMovie)
        peopleAdapterFirst = SearchPeopleAdapterType1(::onClickPeople)
        movieAdapter = SearchAdapterTypeAll(::onClickMovie)
        binding.allRv.adapter = movieAdapter
        binding.allRv.layoutManager = GridLayoutManager(binding.root.context,2)
    }
    private fun getData(type : String,id : Int? = null) {
        when(type) {
            "popular_movies" -> {
                viewModel.getPopularMoviesPaging(type).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        movieAdapter.submitData(it)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
            }
            "top_rated_movies" -> {
                viewModel.getTopRatedMoviesPaging(type).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        movieAdapter.submitData(it)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
            }
            "now_playing_movies" -> {
                viewModel.getNowPlayingMoviesPaging(type).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        movieAdapter.submitData(it)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
            }
            "upcoming_movies" -> {
                viewModel.getUpcomingMoviesPaging(type).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        movieAdapter.submitData(it)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
            }
            "similar_movies" -> {
                viewModel.getSimilarMoviePaging(type,id!!).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        movieAdapter.submitData(it)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
            }
            "recommended_movies" -> {
                viewModel.getRecommendedMoviePaging(type,id!!).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        movieAdapter.submitData(it)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
            }
            "tv_series_cast" -> {
                binding.allRv.adapter = peopleAdapterFirst
                viewModel.getTvSeriesCast(id!!).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        peopleAdapterFirst.updateData(it.body()!!.cast)
                        peopleAdapterFirst.notifyDataSetChanged()
                    }
                }
            }
            "movie_cast" -> {
                binding.allRv.adapter = peopleAdapterFirst
                viewModel.getMovieCast(id!!).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        peopleAdapterFirst.updateData(it.body()!!.cast)
                        peopleAdapterFirst.notifyDataSetChanged()
                    }
                }
            }
            "artist_movies" -> {
                binding.allRv.adapter = movieAdapterFirst
                viewModel.getPeopleMovies(id!!).observe(viewLifecycleOwner) {
                    if (it.isSuccessful && it.body() != null) {
                        movieAdapterFirst.updateData(it.body()!!.cast)
                        movieAdapterFirst.notifyDataSetChanged()
                    }
                }
            }
            "artist_tv_series" -> {
                binding.allRv.adapter = tvSeriesAdapterFirst
                viewModel.getPeopleTvSeries(id!!).observe(viewLifecycleOwner) {
                    if (it.isSuccessful && it.body() != null) {
                        tvSeriesAdapterFirst.updateData(it.body()!!.cast)
                        tvSeriesAdapterFirst.notifyDataSetChanged()
                    }
                }
            }
            "popular_tv_series" -> {
                binding.allRv.adapter = tvSeriesAdapter
                viewModel.getPopularTvSeriesPaging(type,null).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        tvSeriesAdapter.submitData(it)
                        tvSeriesAdapter.notifyDataSetChanged()
                    }
                }
            }
            "top_rated_tv_series" -> {
                binding.allRv.adapter = tvSeriesAdapter
                viewModel.getTopRatedTvSeriesPaging(type,null).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        tvSeriesAdapter.submitData(it)
                        tvSeriesAdapter.notifyDataSetChanged()
                    }
                }
            }
            "on_the_air_tv_series" -> {
                binding.allRv.adapter = tvSeriesAdapter
                viewModel.getOnTheAirTvSeriesPaging(type,null).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        tvSeriesAdapter.submitData(it)
                        tvSeriesAdapter.notifyDataSetChanged()
                    }
                }
            }
            "airing_today_tv_series" -> {
                binding.allRv.adapter = tvSeriesAdapter
                viewModel.getAiringTodayTvSeiresPaging(type,null).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        tvSeriesAdapter.submitData(it)
                        tvSeriesAdapter.notifyDataSetChanged()
                    }
                }
            }
            "similar_tv_series" -> {
                binding.allRv.adapter = tvSeriesAdapter
                viewModel.getSimilarTvSeriesPaging(type,id).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        tvSeriesAdapter.submitData(it)
                        tvSeriesAdapter.notifyDataSetChanged()
                    }
                }
            }
            "recommended_tv_series" -> {
                binding.allRv.adapter = tvSeriesAdapter
                viewModel.getRecommendedTvSeriesPaging(type,id).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        tvSeriesAdapter.submitData(it)
                        tvSeriesAdapter.notifyDataSetChanged()
                    }
                }
            }
            "popular_people" -> {
                binding.allRv.adapter = peopleAdapter
                viewModel.getPopularPeoplePaging(type,id).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        peopleAdapter.submitData(it)
                        peopleAdapterFirst.notifyDataSetChanged()
                    }
                }
            }
            "trending_people" -> {
                binding.allRv.adapter = peopleAdapter
                viewModel.getTrendingPeoplePaging(type,id).observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        peopleAdapter.submitData(it)
                        peopleAdapterFirst.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}