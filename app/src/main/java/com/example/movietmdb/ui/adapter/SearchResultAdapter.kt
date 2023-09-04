package com.example.movietmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.data.model.search.Result
import com.example.movietmdb.databinding.SingleListItemAllBinding

class SearchResultAdapter (private val onClickMovie : (id : Int) -> Unit, private val onClickTvSeries : (id : Int) -> Unit,private val onClickPeople : (id : Int) -> Unit) : PagingDataAdapter<Result, SearchResultViewHolder>(COMPARATOR_SEARCH){
    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item,onClickMovie,onClickTvSeries,onClickPeople)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItemAllBinding>(inflater,
            R.layout.single_list_item_all,parent,false)
        return SearchResultViewHolder(binding)
    }
}
class SearchResultViewHolder(private  val binding : SingleListItemAllBinding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(content : Result, onClickMovie: (id: Int) -> Unit, onClickTvSeries: (id : Int) -> Unit, onClickPeople: (id: Int) -> Unit) {
        var title = content.title
        var uri = "https://image.tmdb.org/t/p/w500/"
        if(title != null) {
            uri += content.posterPath
            binding.layout.setOnClickListener {
                onClickMovie(content.id)
            }
        }else if (content.profilePath == null) {
            title = content.name
            uri += content.posterPath
            binding.layout.setOnClickListener {
                onClickTvSeries(content.id)
            }
        } else {
            title = content.name
            uri += content.profilePath
            binding.layout.setOnClickListener {
                onClickPeople(content.id)
            }
        }

        binding.title.text = title
        Glide.with(binding.image.context)
            .load(uri)
            .into(binding.image)
    }
}
val COMPARATOR_SEARCH = object : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
        // User ID serves as unique ID
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
        // Compare full contents (note: Java users should call .equals())
        oldItem == newItem
}