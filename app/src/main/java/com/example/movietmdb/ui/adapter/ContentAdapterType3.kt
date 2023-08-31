package com.example.movietmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.data.model.content.Content
import com.example.movietmdb.databinding.SingleListItem3Binding
import com.example.movietmdb.ui.util.movieGenreMap

class ContentAdapterType3(private val onClick : (id: Int) -> Unit) : RecyclerView.Adapter<ContentType3ViewHolder>() {
    private var data = ArrayList<Content>()
    fun updateData(data : List<Content>) {
        this.data.clear()
        this.data.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentType3ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItem3Binding>(
            inflater,
            R.layout.single_list_item3,
            parent,
            false
        )
        return ContentType3ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentType3ViewHolder, position: Int) {
        holder.bind(data[position],onClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class ContentType3ViewHolder(private val binding : SingleListItem3Binding)
    : RecyclerView.ViewHolder(binding.root){
    fun bind(content : Content,onClick: (id: Int) -> Unit) {
        binding.textView.text = content.title
        val uri = "https://image.tmdb.org/t/p/w500/" + content.posterPath
        Glide.with(binding.imageView.context)
            .load(uri)
            .into(binding.imageView)
        var genre : String = ""
        val no = 3.coerceAtMost(content.genreIds.size)
        for(i in 0 until no-1) {
            genre += movieGenreMap[content.genreIds[i]] + ", "
        }
        if(no-1 >= 0) genre += movieGenreMap[content.genreIds[no-1]]
        binding.textView2.text = genre
        binding.type3ListItemLayout.setOnClickListener {
            onClick(content.id)
        }
    }
}