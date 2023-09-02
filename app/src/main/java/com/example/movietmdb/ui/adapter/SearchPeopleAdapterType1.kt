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
import com.example.movietmdb.databinding.SingleListItemAllBinding

class SearchPeopleAdapterType1(private val onClick : (id : Int) -> Unit) : RecyclerView.Adapter<SearchPeopleViewHolder>() {
    private var data = ArrayList<People>()
    fun updateData(data : List<People>) {
        this.data.clear()
        this.data.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItemAllBinding>(
            inflater,
            R.layout.single_list_item_all,
            parent,
            false
        )
        return SearchPeopleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchPeopleViewHolder, position: Int) {
        holder.bind(data[position],onClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class SearchPeopleViewHolder(private val binding : SingleListItemAllBinding)
    : RecyclerView.ViewHolder(binding.root){
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