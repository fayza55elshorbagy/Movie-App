package com.example.movieapp.dataLayer.network

import com.example.movieapp.dataLayer.entity.MovieDetails
import com.example.movieapp.dataLayer.entity.SearchedMovie
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    @GET("Search/k_iz2x5w9y/{movie_name}")
    suspend fun searchMovie(@Path("movie_name") movie_name : String): Response<SearchedMovie>

    @GET("Title/k_iz2x5w9y/{id}")
    fun getMovieDetails(@Path("id") id : String) : Observable<MovieDetails>
}