package com.example.movieapp.dataLayer.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.dataLayer.entity.HiddenMovies
import com.example.movieapp.dataLayer.entity.MovieDetails
import retrofit2.Response

@Dao
interface FavouriteDao {


    @Query("SELECT * FROM  MovieDetails")
     fun getAllFavourite(): LiveData<List<MovieDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun saveMovie(movie : MovieDetails)

    @Query("SELECT * FROM  HiddenMovies")
    fun getHiddenMovies() : LiveData<List<HiddenMovies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHiddenMovie(movie : HiddenMovies)


}