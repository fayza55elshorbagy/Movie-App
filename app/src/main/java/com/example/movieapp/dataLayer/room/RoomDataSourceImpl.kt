package com.example.movieapp.dataLayer.room

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.movieapp.dataLayer.entity.HiddenMovies
import com.example.movieapp.dataLayer.entity.MovieDetails
import retrofit2.Response

class RoomDataSourceImpl(var context: Context) : RoomDataSource {
    override  fun getAllFavMovies(): LiveData<List<MovieDetails>> {
        return MovieDatabase.getInstance(context.applicationContext)!!.favouriteDao().getAllFavourite()
    }

    override fun saveMovie(movie: MovieDetails) {
        MovieDatabase.getInstance(context.applicationContext)!!.favouriteDao().saveMovie(movie)
    }

    override  fun getHiddenMovies(): LiveData<List<HiddenMovies>> {
        return MovieDatabase.getInstance(context.applicationContext)!!.favouriteDao().getHiddenMovies()
    }

    override fun saveHiddenMovie(movie: HiddenMovies) {
        MovieDatabase.getInstance(context.applicationContext)!!.favouriteDao().saveHiddenMovie(movie)
    }
}