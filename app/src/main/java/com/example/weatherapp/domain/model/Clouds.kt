package com.example.weatherapp.domain.model

import com.google.gson.annotations.SerializedName


data class Clouds(

    @SerializedName("all") var all: Int? = null

)