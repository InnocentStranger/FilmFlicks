package com.example.movietmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movietmdb.R
import com.example.movietmdb.data.model.content.Genre
import com.example.movietmdb.databinding.GenreItemBinding

class GenreAdapterTypeBox : RecyclerView.Adapter<GenreViewHolder>() {
    private var genreList = ArrayList<Genre>()
    fun updateGenre(genre : List<Genre>) {
        genreList.clear()
        genreList.addAll(genre)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<GenreItemBinding>(inflater, R.layout.genre_item,parent,false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genreList[position])
    }

    override fun getItemCount(): Int {
        return genreList.size
    }
}
class GenreViewHolder(private val binding : GenreItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(genre : Genre){
        binding.genre.text = genre.name
    }
}