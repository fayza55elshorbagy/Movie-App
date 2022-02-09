package com.example.movieapp.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.dataLayer.Repository
import com.example.movieapp.ui.homeScreen.HomeViewModel
import com.example.movieapp.ui.movieDetailsScreen.MovieDetailsViewModel
import com.example.movieapp.ui.searchScreen.SearchedMovieViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: Repository,
    private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchedMovieViewModel::class.java)) {
            return SearchedMovieViewModel(repository, application) as T
        }
       else if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(repository, application) as T
        }
        else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository, application) as T
        }
        else{
            throw IllegalArgumentException("ViewModel Not Found")

        }
    }
}