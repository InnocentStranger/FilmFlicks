package com.example.movietmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.data.model.content.Content
import com.example.movietmdb.databinding.SingleListItem2Binding

class ContentAdapterType2(private val onClick : (id: Int) -> Unit) : RecyclerView.Adapter<ContentType2ViewHolder>() {
    private var data = ArrayList<Content>()
    fun updateData(data : List<Content>) {
        this.data.clear()
        this.data.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentType2ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItem2Binding>(
            inflater,
            R.layout.single_list_item2,
            parent,
            false
        )
        return ContentType2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentType2ViewHolder, position: Int) {
        holder.bind(data[position],onClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class ContentType2ViewHolder(private val binding : SingleListItem2Binding)
    : RecyclerView.ViewHolder(binding.root){
    fun bind(content : Content,onClick: (id: Int) -> Unit) {
        binding.title.text = content.title
        val uri = "https://image.tmdb.org/t/p/w500/" + content.posterPath
        Glide.with(binding.image.context)
            .load(uri)
            .into(binding.image)
        binding.image.setOnClickListener {
            onClick(content.id)
        }
    }
}