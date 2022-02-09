package com.example.movieapp.dataLayer.network

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.dataLayer.entity.MovieDetails
import com.example.movieapp.dataLayer.entity.SearchedMovie
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Path

interface RemoteDataSource {
    suspend fun getSearchedMovieList(movieName : String): Response<SearchedMovie>
    fun getMovieDetails(id : String) : Observable<MovieDetails>

}