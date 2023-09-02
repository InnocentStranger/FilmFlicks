package com.example.movietmdb.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietmdb.R
import com.example.movietmdb.databinding.FragmentPeopleBinding
import com.example.movietmdb.ui.adapter.PeopleAdapterType1
import com.example.movietmdb.ui.adapter.PeopleAdapterType2
import com.example.movietmdb.ui.viewmodel.HomeViewModel

class PeopleFragment : Fragment() {
    private lateinit var binding : FragmentPeopleBinding
    private val viewModel : HomeViewModel by activityViewModels()
    private lateinit var popularAdapter : PeopleAdapterType1
    private lateinit var trendingAdapter : PeopleAdapterType2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_people, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        getData()
        onClickAll()
    }
    private fun onClickPeople(id : Int) {
        val bundle = Bundle()
        bundle.putString("id",id.toString())
        findNavController().navigate(R.id.action_peopleFragment_to_peopleDetailFragment,bundle)
    }
    private fun onClickAll() {
        binding.popularAll.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type","popular_people")
            findNavController().navigate(R.id.action_peopleFragment_to_seeAllFragment,bundle)
        }
        binding.trendingAll.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type","trending_people")
            findNavController().navigate(R.id.action_peopleFragment_to_seeAllFragment,bundle)
        }
    }
    private fun initRv() {
        popularAdapter = PeopleAdapterType1(::onClickPeople)
        trendingAdapter = PeopleAdapterType2(::onClickPeople)
        binding.popularRv.adapter = popularAdapter
        binding.popularRv.layoutManager = GridLayoutManager(
            binding.root.context,2,GridLayoutManager.HORIZONTAL,false)
        binding.trendingRv.adapter = trendingAdapter
        binding.trendingRv.layoutManager = GridLayoutManager(binding.root.context,3,GridLayoutManager.HORIZONTAL,false)
    }
    private fun getData() {
        viewModel.getPopularPeopleFirstPage().observe(viewLifecycleOwner, Observer{
            if(it.isSuccessful && it.body() != null) {
                popularAdapter.updateData(it.body()!!.itemList)
                popularAdapter.notifyDataSetChanged()
            }
        })

        viewModel.getTrendingPeopleFirstPage().observe(viewLifecycleOwner,Observer{
            if(it.isSuccessful && it.body() != null) {
                trendingAdapter.updateData(it.body()!!.itemList)
                trendingAdapter.notifyDataSetChanged()
            }
        })
    }
}