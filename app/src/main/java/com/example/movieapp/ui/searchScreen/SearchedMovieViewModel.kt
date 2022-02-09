package com.example.movieapp.ui.searchScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.movieapp.dataLayer.Repository
import com.example.movieapp.dataLayer.entity.HiddenMovies
import com.example.movieapp.dataLayer.entity.MovieDetails
import com.example.movieapp.dataLayer.entity.SearchedMovie
import io.reactivex.Observable
import retrofit2.Response

class SearchedMovieViewModel (val repository: Repository,application: Application) :
    AndroidViewModel(application) {



    suspend fun getSearchedMovie(movieName:String): Response<SearchedMovie> {
        return repository.getSearchedMovie(movieName)
    }

     fun getHiddenMovies() : LiveData<List<HiddenMovies>>{
        return repository.getHiddenMovies()
    }
    fun saveHiddenMovie(movie: HiddenMovies){
        repository.saveHiddenMovie(movie)
    }

    fun getMovieDetails(id:String): Observable<MovieDetails> {
        return repository.getMovieDetails(id)
    }


}