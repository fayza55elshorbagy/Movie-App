package com.example.movieapp.ui.movieDetailsScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.DirectorList
import com.example.movieapp.R
import com.example.movieapp.dataLayer.entity.SearchedMovie

class OptionsAdapter(
    private var options : List<DirectorList>):
    RecyclerView.Adapter<OptionsAdapter.ViewHolderExample>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExample {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.options_item,parent,false)
        return ViewHolderExample(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderExample, position: Int) {
        holder.itemActor.text = options[position].name
    }

    override fun getItemCount() = options.size



    class ViewHolderExample(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemActor: TextView = itemView.findViewById(R.id.itemRecycler)


    }



}