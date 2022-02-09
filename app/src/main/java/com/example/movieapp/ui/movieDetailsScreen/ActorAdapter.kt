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
import com.example.example.ActorList
import com.example.movieapp.R
import com.example.movieapp.dataLayer.entity.SearchedMovie

class ActorAdapter(
    private val context: Context,
    private val item: List<ActorList>):
    RecyclerView.Adapter<ActorAdapter.ViewHolderExample>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExample {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.actor_item,parent,false)
        return ViewHolderExample(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderExample, position: Int) {
        holder.itemName.text = item.get(position).name
        Glide.with(context)
            .load( item.get(position).image )
            .into(holder.itemIcon)


    }

    override fun getItemCount() = item.size



    class ViewHolderExample(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.findViewById(R.id.itemTitle)
        val itemIcon : ImageView = itemView.findViewById(R.id.itemIcon)

    }



}