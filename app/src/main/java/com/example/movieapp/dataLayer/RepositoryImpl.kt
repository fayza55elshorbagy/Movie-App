package com.example.movieapp.dataLayer

import androidx.lifecycle.LiveData
import com.example.movieapp.dataLayer.entity.HiddenMovies
import com.example.movieapp.dataLayer.entity.MovieDetails
import com.example.movieapp.dataLayer.entity.SearchedMovie
import com.example.movieapp.dataLayer.network.RemoteDataSource
import com.example.movieapp.dataLayer.room.RoomDataSource
import io.reactivex.Observable
import retrofit2.Response

class RepositoryImpl(var remoteDataSource: RemoteDataSource,var roomDataSource: RoomDataSource) : Repository {
    override suspend fun getSearchedMovie(movieName: String): Response<SearchedMovie> {
        return remoteDataSource.getSearchedMovieList(movieName)
    }

    override  fun getAllFavMovies(): LiveData<List<MovieDetails>> {
        return roomDataSource.getAllFavMovies()
    }


    override fun saveMovie(movie: MovieDetails) {
        roomDataSource.saveMovie(movie)
    }

    override fun getMovieDetails(id: String): Observable<MovieDetails> {
        return remoteDataSource.getMovieDetails(id)
    }

    override  fun getHiddenMovies(): LiveData<List<HiddenMovies>> {

        return roomDataSource.getHiddenMovies()
    }

    override fun saveHiddenMovie(movie: HiddenMovies) {
        roomDataSource.saveHiddenMovie(movie)
    }
}