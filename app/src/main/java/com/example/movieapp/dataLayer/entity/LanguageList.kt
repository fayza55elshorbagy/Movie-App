package com.example.example

import com.google.gson.annotations.SerializedName


data class LanguageList (

  @SerializedName("key"   ) var key   : String? = null,
  @SerializedName("value" ) var value : String? = null

)