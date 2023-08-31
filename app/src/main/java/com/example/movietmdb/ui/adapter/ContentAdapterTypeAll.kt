package com.example.movietmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.data.model.content.Content
import com.example.movietmdb.databinding.SingleListItem1Binding

class ContentAdapterTypeAll : PagingDataAdapter<Content,ContentAllViewHolder>(COMPARATOR){
    override fun onBindViewHolder(holder: ContentAllViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAllViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItem1Binding>(inflater,R.layout.single_list_item1,parent,false)
        return ContentAllViewHolder(binding)
    }
}
class ContentAllViewHolder(private  val binding : SingleListItem1Binding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(content : Content) {
        binding.title.text = content.title
        val uri = "https://image.tmdb.org/t/p/w500/" + content.posterPath
        Glide.with(binding.image.context)
            .load(uri)
            .into(binding.image)
    }
}
val COMPARATOR = object : DiffUtil.ItemCallback<Content>() {
    override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean =
        // User ID serves as unique ID
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean =
        // Compare full contents (note: Java users should call .equals())
        oldItem == newItem
}