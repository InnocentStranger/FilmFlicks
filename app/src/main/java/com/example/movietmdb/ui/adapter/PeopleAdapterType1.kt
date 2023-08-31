package com.example.movietmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.data.model.content.Content
import com.example.movietmdb.data.model.people.People
import com.example.movietmdb.databinding.SingleListItem1Binding

class PeopleAdapterType1 : RecyclerView.Adapter<PeopleViewHolder>() {
    private var data = ArrayList<People>()
    fun updateData(data : List<People>) {
        this.data.clear()
        this.data.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItem1Binding>(
            inflater,
            R.layout.single_list_item1,
            parent,
            false
        )
        return PeopleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class PeopleViewHolder(private val binding : SingleListItem1Binding)
    : RecyclerView.ViewHolder(binding.root){
    fun bind(people : People) {
        binding.title.text = people.name
        val uri = "https://image.tmdb.org/t/p/w500/" + people.profilePath
        Glide.with(binding.image.context)
            .load(uri)
            .into(binding.image)
    }
}