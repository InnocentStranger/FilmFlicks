package com.example.movietmdb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietmdb.R
import com.example.movietmdb.data.model.people.People
import com.example.movietmdb.databinding.SingleListItem2Binding
import com.example.movietmdb.databinding.SingleListItem3Binding

class PeopleAdapterType2(private val onClick : (id : Int) -> Unit) : RecyclerView.Adapter<PeopleType2ViewHolder>() {
    private var data = ArrayList<People>()
    fun updateData(data : List<People>) {
        this.data.clear()
        this.data.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleType2ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleListItem3Binding>(
            inflater,
            R.layout.single_list_item3,
            parent,
            false
        )
        return PeopleType2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleType2ViewHolder, position: Int) {
        holder.bind(data[position],onClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class PeopleType2ViewHolder(private val binding : SingleListItem3Binding)
    : RecyclerView.ViewHolder(binding.root){
    fun bind(people : People,onClick: (id: Int) -> Unit) {
        binding.textView.text = people.name
        val uri = "https://image.tmdb.org/t/p/w500/" + people.profilePath
        Glide.with(binding.imageView.context)
            .load(uri)
            .into(binding.imageView)
        binding.textView2.text = people.knownForDepartment
        binding.imageView.setOnClickListener {
            onClick(people.id)
        }
    }
}