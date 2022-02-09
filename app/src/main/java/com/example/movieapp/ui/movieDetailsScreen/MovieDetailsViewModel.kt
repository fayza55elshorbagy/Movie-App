package com.example.movieapp.ui.movieDetailsScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.dataLayer.Repository
import com.example.movieapp.dataLayer.entity.MovieDetails
import com.example.movieapp.dataLayer.entity.SearchedMovie
import io.reactivex.Observable

class MovieDetailsViewModel (val repository: Repository, application: Application) :
    AndroidViewModel(application) {

    fun getMovieDetails(id:String): Observable<MovieDetails> {
        return repository.getMovieDetails(id)
    }
    fun saveFavMovie(movie:MovieDetails){
        repository.saveMovie(movie)
    }



}