package com.example.movieapp.ui.homeScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.dataLayer.entity.MovieDetails
import com.example.movieapp.dataLayer.entity.SearchedMovie
import com.example.movieapp.ui.base.ItemsRecyclerClick
import org.w3c.dom.Text

class HomeAdapter(
    private val context: Context,
    private val item: List<MovieDetails>,
    var onClick: ItemsRecyclerClick
):
    RecyclerView.Adapter<HomeAdapter.ViewHolderExample>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExample {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fav_item,parent,false)
        return ViewHolderExample(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderExample, position: Int) {
        holder.itemName.text = item[position].title
        holder.geners.text = item[position].genres
        Glide.with(context)
            .load( item[position].image )
            .into(holder.itemIcon)

        holder.itemView.setOnClickListener {
            onClick.itemOnClick(item[position].id)
        }
    }

    override fun getItemCount() = item.size



    class ViewHolderExample(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.findViewById(R.id.favItemTitle)
        val itemIcon : ImageView = itemView.findViewById(R.id.itemIcon)
        val geners : TextView = itemView.findViewById(R.id.geners)
        val rate : TextView = itemView.findViewById(R.id.fav_rate)

    }



}