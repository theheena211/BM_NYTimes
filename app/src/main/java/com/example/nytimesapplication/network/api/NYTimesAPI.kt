package com.example.nytimesapplication.network.api

import com.example.nytimesapplication.data.model.MostPopularResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

    interface  NYTimesApi {
        @GET("svc/mostpopular/v2/mostviewed/{section}/{period}.json")
        suspend fun getMostPopularArticles(@Path("section") section : String,
                                           @Path("period") period : Int,
                                           @Query("api-key") apiKey : String): MostPopularResponse
    }
