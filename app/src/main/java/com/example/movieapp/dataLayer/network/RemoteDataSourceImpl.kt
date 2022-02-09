package com.example.movieapp.dataLayer.network

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.dataLayer.entity.MovieDetails
import com.example.movieapp.dataLayer.entity.SearchedMovie
import io.reactivex.Observable
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun getSearchedMovieList(movieName : String): Response<SearchedMovie> {
        return Network.apiService.searchMovie(movieName)
    }

    override fun getMovieDetails(id: String): Observable<MovieDetails> {
        return Network.apiService.getMovieDetails(id)
    }
}