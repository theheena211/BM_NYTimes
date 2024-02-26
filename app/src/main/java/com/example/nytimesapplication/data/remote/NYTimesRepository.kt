package com.example.nytimesapplication.data.remote

import com.example.nytimesapplication.data.model.MostPopularResponse
import com.example.nytimesapplication.network.api.NYTimesApi

open class NYTimesRepository {
    var api: NYTimesApi

    init {
        api = RetrofitInstance.api
    }

    suspend open fun getMostPopularArticles(section : String, period : Int, apiKey : String): MostPopularResponse {
        return api.getMostPopularArticles(section, period, apiKey)
    }
}