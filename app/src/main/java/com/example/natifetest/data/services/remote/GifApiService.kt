package com.example.natifetest.data.services.remote

import com.example.natifetest.domain.entities.networkEntities.GifResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApiService {
    @GET("gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): GifResponse
}
