package com.example.movietmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.data.model.content.Content
import com.example.movietmdb.databinding.SingleListItem1Binding

class ContentAdapterType1(private val onClick : (id: Int) -> Unit) : RecyclerView.Adapter<ContentViewHolder>() {
    private var data = ArrayList<Content>()
    fun updateData(data : List<Content>) {
        this.data.clear()
        this.data.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItem1Binding>(
            inflater,
            R.layout.single_list_item1,
            parent,
            false
        )
        return ContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(data[position],onClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class ContentViewHolder(private val binding : SingleListItem1Binding)
    : RecyclerView.ViewHolder(binding.root){
    fun bind(content : Content,onClick : (id: Int) -> Unit) {
        binding.title.text = content.title
        val uri = "https://image.tmdb.org/t/p/w500/" + content.posterPath
        Glide.with(binding.image.context)
            .load(uri)
            .into(binding.image)
        binding.cardView.setOnClickListener{
            onClick(content.id)
        }
    }
}