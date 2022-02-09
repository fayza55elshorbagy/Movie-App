package com.example.movieapp.dataLayer.entity


import com.google.gson.annotations.SerializedName

data class SearchedMovie(

    @SerializedName("searchType") val searchType : String,
    @SerializedName("expression") val expression : String,
    @SerializedName("results") val results : List<Results>,
    @SerializedName("errorMessage") val errorMessage : String
    )
