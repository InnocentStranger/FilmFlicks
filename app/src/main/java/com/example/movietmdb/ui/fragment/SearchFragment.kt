package com.example.movietmdb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movietmdb.R
import com.example.movietmdb.databinding.FragmentSearchBinding
import com.example.movietmdb.ui.adapter.SearchResultAdapter
import com.example.movietmdb.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding
    private lateinit var adapter : SearchResultAdapter
    private val viewModel : HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchResultAdapter(::onClickMovie,::onClickTvSeries,::onClickPeople)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(binding.root.context,2,GridLayoutManager.VERTICAL,false)
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null) {
                    viewModel.getSearchResult(query).observe(viewLifecycleOwner, Observer {
                        lifecycleScope.launch {
                            adapter.submitData(it)
                            adapter.notifyDataSetChanged()
                        }
                    })
                }
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null) {
                    viewModel.getSearchResult(newText).observe(viewLifecycleOwner, Observer {
                        lifecycleScope.launch {
                            adapter.submitData(it)
                            adapter.notifyDataSetChanged()
                        }
                    })
                }
                return false
            }
        })

    }

    private fun onClickPeople(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_searchFragment_to_peopleDetailFragment,bundle)
    }
    private fun onClickMovie(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_searchFragment_to_movieDetailFragment,bundle)
    }
    private fun onClickTvSeries(id : Int) {
        val bundle  = Bundle()
        bundle.putString("id", id.toString())
        findNavController().navigate(R.id.action_searchFragment_to_tvSeriesDetailsFragment,bundle)
    }
}