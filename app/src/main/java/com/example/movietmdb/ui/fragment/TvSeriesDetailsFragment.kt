package com.example.movietmdb.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.databinding.FragmentTvSeriesDetailsBinding
import com.example.movietmdb.ui.adapter.ContentAdapterType2
import com.example.movietmdb.ui.adapter.GenreAdapterTypeBox
import com.example.movietmdb.ui.adapter.PeopleAdapterType1
import com.example.movietmdb.ui.viewmodel.HomeViewModel

class TvSeriesDetailsFragment : Fragment() {
    private lateinit var binding : FragmentTvSeriesDetailsBinding
    private lateinit var genreAdapter : GenreAdapterTypeBox
    private lateinit var castAdapter : PeopleAdapterType1
    private lateinit var similarContentAdapter : ContentAdapterType2
    private lateinit var recommendedContentAdapter : ContentAdapterType2
    private val viewModel : HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tv_series_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvId = this.arguments?.getString("id")?.toInt()
        initRv()
        if(tvId != null) getData(tvId)
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        if(tvId != null) allClick(tvId)
    }
    private fun allClick(movieId: Int) {
        binding.allCast.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type","tv_series_cast")
            bundle.putString("id",movieId.toString())
            findNavController().navigate(R.id.action_tvSeriesDetailsFragment_to_seeAllFragment,bundle)
        }
        binding.similarAll.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type","similar_tv_series")
            bundle.putString("id",movieId.toString())
            findNavController().navigate(R.id.action_tvSeriesDetailsFragment_to_seeAllFragment,bundle)
        }
        binding.recommendedAll.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type","recommended_tv_series")
            bundle.putString("id",movieId.toString())
            findNavController().navigate(R.id.action_tvSeriesDetailsFragment_to_seeAllFragment,bundle)
        }
    }
    private fun onClickContent(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_tvSeriesDetailsFragment_self,bundle)
    }
    private fun onClickPeople(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_tvSeriesDetailsFragment_to_peopleDetailFragment,bundle)
    }
    private fun initRv() {
        genreAdapter = GenreAdapterTypeBox()
        binding.genreRv.adapter = genreAdapter
        binding.genreRv.layoutManager = LinearLayoutManager(binding.root.context,
            LinearLayoutManager.HORIZONTAL,false)

        castAdapter = PeopleAdapterType1(::onClickPeople)
        binding.castRv.adapter = castAdapter
        binding.castRv.layoutManager = LinearLayoutManager(binding.root.context,
            LinearLayoutManager.HORIZONTAL,false)

        similarContentAdapter = ContentAdapterType2(::onClickContent)
        binding.similarRv.adapter = similarContentAdapter
        binding.similarRv.layoutManager = LinearLayoutManager(binding.root.context,
            LinearLayoutManager.HORIZONTAL,false)

        recommendedContentAdapter = ContentAdapterType2(::onClickContent)
        binding.recommendedRv.adapter = recommendedContentAdapter
        binding.recommendedRv.layoutManager = LinearLayoutManager(binding.root.context,
            LinearLayoutManager.HORIZONTAL,false)
    }

    private fun getData(tvId : Int) {
        viewModel.getTvSeriesDetails(tvId).observe(viewLifecycleOwner) {
            if (it.isSuccessful && it.body() != null) {
                var uri = "https://image.tmdb.org/t/p/w500/" + it.body()!!.backdropPath
                Glide.with(binding.backgroundImage.context)
                    .load(uri)
                    .into(binding.backgroundImage)
                uri = "https://image.tmdb.org/t/p/w500/" + it.body()!!.posterPath
                Glide.with(binding.image.context)
                    .load(uri)
                    .into(binding.image)
                var title = it.body()?.title
                if (title == null) title = it.body()?.name
                binding.title.text = title
                binding.description.text = it.body()!!.overview
                binding.votes.text = "( ${it.body()!!.voteCount} )"
                binding.ratingBar.rating = it.body()!!.voteAverage.toFloat() / 2

                genreAdapter.updateGenre(it.body()!!.genres)
                genreAdapter.notifyDataSetChanged()
            }

        }

        viewModel.getTvSeriesCast(tvId).observe(viewLifecycleOwner) {
            if (it.isSuccessful && it.body() != null) {
                castAdapter.updateData(it.body()!!.cast)
                castAdapter.notifyDataSetChanged()
            }
        }

        viewModel.getSimilarTvSeriesFirstPage(tvId).observe(viewLifecycleOwner) {
            if (it.isSuccessful && it.body() != null) {
                similarContentAdapter.updateData(it.body()!!.itemList)
                similarContentAdapter.notifyDataSetChanged()
            }
        }

        viewModel.getRecommendedTvSeriesFirstPage(tvId).observe(viewLifecycleOwner) {
            if (it.isSuccessful && it.body() != null) {
                recommendedContentAdapter.updateData(it.body()!!.itemList)
                recommendedContentAdapter.notifyDataSetChanged()
            }
        }
    }
}