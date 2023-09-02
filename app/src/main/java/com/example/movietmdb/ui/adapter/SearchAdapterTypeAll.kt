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
import com.example.movietmdb.databinding.SingleListItemAllBinding

class SearchAdapterTypeAll(private val onClick : (id : Int) -> Unit) : PagingDataAdapter<Content,ContentAllViewHolder>(COMPARATOR){
    override fun onBindViewHolder(holder: ContentAllViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item,onClick)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAllViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItemAllBinding>(inflater,R.layout.single_list_item_all,parent,false)
        return ContentAllViewHolder(binding)
    }
}
class ContentAllViewHolder(private  val binding : SingleListItemAllBinding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(content : Content,onClick: (id: Int) -> Unit) {
        var title = content.title
        if(title == null) title = content.name
        binding.title.text = title
        val uri = "https://image.tmdb.org/t/p/w500/" + content.posterPath
        Glide.with(binding.image.context)
            .load(uri)
            .into(binding.image)
        binding.layout.setOnClickListener {
            onClick(content.id)
        }
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