package com.example.movietmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.data.model.content.Content
import com.example.movietmdb.databinding.SingleListItem1Binding
import com.example.movietmdb.databinding.SingleListItemAllBinding

class SearchAdapterType1(private val onClick : (id: Int) -> Unit) : RecyclerView.Adapter<SearchType1ViewHolder>() {
    private var data = ArrayList<Content>()
    fun updateData(data : List<Content>) {
        this.data.clear()
        this.data.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchType1ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItemAllBinding>(
            inflater,
            R.layout.single_list_item_all,
            parent,
            false
        )
        return SearchType1ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchType1ViewHolder, position: Int) {
        holder.bind(data[position],onClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class SearchType1ViewHolder(private val binding : SingleListItemAllBinding)
    : RecyclerView.ViewHolder(binding.root){
    fun bind(content : Content,onClick : (id: Int) -> Unit) {
        var title = content.title
        if(title == null) title = content.name
        binding.title.text = title
        val uri = "https://image.tmdb.org/t/p/w500/" + content.posterPath
        Glide.with(binding.image.context)
            .load(uri)
            .into(binding.image)
        binding.layout.setOnClickListener{
            onClick(content.id)
        }
    }
}