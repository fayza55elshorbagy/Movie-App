package com.example.movieapp.dataLayer.entity

import androidx.room.TypeConverter
import com.example.example.ActorList
import com.example.example.DirectorList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

class Converter {

    //Result
    @TypeConverter
    fun convertResultstoObject(data: String?): List<Results?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<Results?>?>() {}.type
        return gson.fromJson<List<Results?>>(data, listType)
    }

    @TypeConverter
    fun convertResultstoString(myObjects: List<Results?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    //Director class

    @TypeConverter
    fun convertDirectortoObject(data: String?): List<DirectorList?>?  {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<Results?>?>() {}.type
        return gson.fromJson<List<DirectorList?>>(data, listType)
    }

    @TypeConverter
    fun convertDirectortoString(myObjects: List<DirectorList?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

// Actorlist
    @TypeConverter
    fun convertActorlisttoObject(data: String?): List<ActorList?>?  {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<Results?>?>() {}.type
        return gson.fromJson<List<ActorList?>>(data, listType)
    }

    @TypeConverter
    fun convertActorlisttoString(myObjects: List<ActorList?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }


}