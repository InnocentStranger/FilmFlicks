package com.example.movietmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.data.model.people.People
import com.example.movietmdb.databinding.SingleListItem1Binding
import com.example.movietmdb.databinding.SingleListItemAllBinding

class SearchPeopleAdapterTypeAll(private val onClick : (id : Int) -> Unit) : PagingDataAdapter<People, SearchPeopleAllViewHolder>(COMPARATOR_PEOPLE){
    override fun onBindViewHolder(holder: SearchPeopleAllViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item,onClick)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPeopleAllViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItemAllBinding>(inflater,
            R.layout.single_list_item_all,parent,false)
        return SearchPeopleAllViewHolder(binding)
    }
}
class SearchPeopleAllViewHolder(private  val binding : SingleListItemAllBinding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(people : People,onClick: (id: Int) -> Unit) {
        binding.title.text = people.name
        val uri = "https://image.tmdb.org/t/p/w500/" + people.profilePath
        Glide.with(binding.image.context)
            .load(uri)
            .into(binding.image)
        binding.layout.setOnClickListener {
            onClick(people.id)
        }
    }
}
