package com.example.movieapp.dataLayer

import androidx.lifecycle.LiveData
import com.example.movieapp.dataLayer.entity.HiddenMovies
import com.example.movieapp.dataLayer.entity.MovieDetails
import com.example.movieapp.dataLayer.entity.SearchedMovie
import io.reactivex.Observable
import retrofit2.Response

interface Repository {

    suspend fun getSearchedMovie(movieName:String):Response<SearchedMovie>
    fun getAllFavMovies():LiveData<List<MovieDetails>>
    fun saveMovie(movie: MovieDetails)
    fun getMovieDetails(id : String) : Observable<MovieDetails>
    fun getHiddenMovies() : LiveData<List<HiddenMovies>>
    fun saveHiddenMovie(movie: HiddenMovies)

}