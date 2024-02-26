package com.example.nytimesapplication.data.remote

import com.example.nytimesapplication.network.api.NYTimesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.nytimes.com/"

    val api: NYTimesApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(NYTimesApi::class.java)
    }
}