package com.example.example

import com.google.gson.annotations.SerializedName


data class DirectorList (

  @SerializedName("id"   ) var id   : String? = null,
  @SerializedName("name" ) var name : String? = null

)