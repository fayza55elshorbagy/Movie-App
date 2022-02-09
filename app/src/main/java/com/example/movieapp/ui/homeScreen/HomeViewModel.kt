package com.example.movieapp.ui.homeScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.dataLayer.Repository
import com.example.movieapp.dataLayer.entity.MovieDetails
import com.example.movieapp.dataLayer.entity.SearchedMovie
import io.reactivex.Observable
import retrofit2.Response

class HomeViewModel (val repository: Repository, application: Application) :
    AndroidViewModel(application) {


     fun getAllFav(): LiveData<List<MovieDetails>>{
        return repository.getAllFavMovies()
    }




}