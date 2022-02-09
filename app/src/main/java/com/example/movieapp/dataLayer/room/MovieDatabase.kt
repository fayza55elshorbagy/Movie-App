package com.example.movieapp.dataLayer.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.dataLayer.entity.Converter
import com.example.movieapp.dataLayer.entity.HiddenMovies
import com.example.movieapp.dataLayer.entity.MovieDetails


@TypeConverters(Converter::class)
@Database(entities = [HiddenMovies::class, MovieDetails::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var db: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {
            synchronized(this) {
                if (db == null)
                    db = Room.databaseBuilder(
                        context.applicationContext, MovieDatabase::class.java, "FavouriteMovies"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return db
        }


    }


    abstract fun favouriteDao(): FavouriteDao

}
