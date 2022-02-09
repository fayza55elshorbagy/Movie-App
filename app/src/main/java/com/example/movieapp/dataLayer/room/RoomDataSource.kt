package com.example.movieapp.dataLayer.room

import androidx.lifecycle.LiveData
import com.example.movieapp.dataLayer.entity.HiddenMovies
import com.example.movieapp.dataLayer.entity.MovieDetails
import retrofit2.Response

interface RoomDataSource {
     fun getAllFavMovies(): LiveData<List<MovieDetails>>
    fun saveMovie(movie : MovieDetails)
    fun getHiddenMovies() : LiveData<List<HiddenMovies>>
    fun saveHiddenMovie(movie: HiddenMovies)
}