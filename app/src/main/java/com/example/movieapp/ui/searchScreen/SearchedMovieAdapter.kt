package com.example.movieapp.ui.searchScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.dataLayer.entity.Results
import com.example.movieapp.dataLayer.entity.SearchedMovie
import com.example.movieapp.ui.base.ItemsRecyclerClick

class SearchedMovieAdapter(
    private val context: Context,
    private val item: MutableList<Results>,
    var onClick: ItemsRecyclerClick
):
    RecyclerView.Adapter<SearchedMovieAdapter.ViewHolderExample>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExample {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_item,parent,false)
        return ViewHolderExample(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderExample, position: Int) {
        holder.itemName.text = item.get(position).title
        holder.description.text = item.get(position).description
        Glide.with(context)
            .load( item.get(position).image )
            .into(holder.itemIcon)

        holder.itemView.setOnClickListener {
            onClick.itemOnClick(item.get(position).id)
        }

        holder.deleteIcon.setOnClickListener {
            onClick.deleteMovie(item.get(position).id)
        }


    }

    override fun getItemCount() = item.size



    class ViewHolderExample(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.findViewById(R.id.item_movie)
        val itemIcon : ImageView = itemView.findViewById(R.id.item_poster)
        val description : TextView = itemView.findViewById(R.id.item_description)
        val deleteIcon : ImageView = itemView.findViewById(R.id.delete)

    }



}