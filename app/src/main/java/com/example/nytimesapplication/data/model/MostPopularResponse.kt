package com.example.nytimesapplication.data.model

import com.google.gson.annotations.SerializedName

data class MostPopularResponse(@SerializedName("results") val results: List<MostPopularArticle>?)
