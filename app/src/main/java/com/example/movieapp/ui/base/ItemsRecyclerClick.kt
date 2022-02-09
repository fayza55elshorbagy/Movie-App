package com.example.movieapp.ui.base

import com.example.movieapp.dataLayer.entity.SearchedMovie

interface ItemsRecyclerClick {
    fun itemOnClick(itemId:String)
    fun deleteMovie(itemId:String)
}