package com.example.movieapp.dataLayer.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.example.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MovieDetails")
data class MovieDetails(

        @PrimaryKey
        @SerializedName("id"               ) val id               : String,
        @SerializedName("title"            ) var title            : String?                 = null,
        @SerializedName("originalTitle"    ) var originalTitle    : String?                 = null,
        @SerializedName("fullTitle"        ) var fullTitle        : String?                 = null,
        @SerializedName("type"             ) var type             : String?                 = null,
        @SerializedName("year"             ) var year             : String?                 = null,
        @SerializedName("image"            ) var image            : String?                 = null,
        @SerializedName("releaseDate"      ) var releaseDate      : String?                 = null,
        @SerializedName("awards"           ) var awards           : String?                 = null,
        @SerializedName("directors"        ) var directors        : String?                 = null,
        @SerializedName("directorList"     ) var directorList     : List<DirectorList> = arrayListOf(),
        @SerializedName("writers"          ) var writers          : String?                 = null,
        @SerializedName("stars"            ) var stars            : String?                 = null,
        @SerializedName("actorList"        ) var actorList        : List<ActorList>    = arrayListOf(),
        @SerializedName("fullCast"         ) var fullCast         : String?                 = null,
        @SerializedName("countries"        ) var countries        : String?                 = null,
        @SerializedName("languages"        ) var languages        : String?                 = null,
        @SerializedName("contentRating"    ) var contentRating    : String?                 = null,
        @SerializedName("imDbRating"       ) var imDbRating       : String?                 = null,
        @SerializedName("imDbRatingVotes"  ) var imDbRatingVotes  : String?                 = null,
        @SerializedName("metacriticRating" ) var metacriticRating : String?                 = null,
        @SerializedName("ratings"          ) var ratings          : String?                 = null,
        @SerializedName("wikipedia"        ) var wikipedia        : String?                 = null,
        @SerializedName("posters"          ) var posters          : String?                 = null,
        @SerializedName("images"           ) var images           : String?                 = null,
        @SerializedName("errorMessage"     ) var errorMessage     : String?                 = null,
        @SerializedName("genres"           ) var genres           : String?                 = null

)
