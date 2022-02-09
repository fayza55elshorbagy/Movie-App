package com.example.movieapp.dataLayer.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

@Entity(tableName = "HiddenMovies")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class HiddenMovies(
    @PrimaryKey(autoGenerate = true)
    val hidden_id : Int,
    @Embedded
    val movieDetails: MovieDetails )
