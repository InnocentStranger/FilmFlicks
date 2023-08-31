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

class PeopleAdapterTypeAll : PagingDataAdapter<People, PeopleAllViewHolder>(COMPARATOR_PEOPLE){
    override fun onBindViewHolder(holder: PeopleAllViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAllViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItem1Binding>(inflater,
            R.layout.single_list_item1,parent,false)
        return PeopleAllViewHolder(binding)
    }
}
class PeopleAllViewHolder(private  val binding : SingleListItem1Binding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(people : People) {
        binding.title.text = people.name
        val uri = "https://image.tmdb.org/t/p/w500/" + people.profilePath
        Glide.with(binding.image.context)
            .load(uri)
            .into(binding.image)
    }
}
val COMPARATOR_PEOPLE = object : DiffUtil.ItemCallback<People>() {
    override fun areItemsTheSame(oldItem: People, newItem: People): Boolean =
        // User ID serves as unique ID
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: People, newItem: People): Boolean =
        // Compare full contents (note: Java users should call .equals())
        oldItem == newItem
}