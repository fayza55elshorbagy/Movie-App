package com.example.example

import com.google.gson.annotations.SerializedName


data class ActorList (

  @SerializedName("id"          ) var id          : String? = null,
  @SerializedName("image"       ) var image       : String? = null,
  @SerializedName("name"        ) var name        : String? = null,
  @SerializedName("asCharacter" ) var asCharacter : String? = null

)