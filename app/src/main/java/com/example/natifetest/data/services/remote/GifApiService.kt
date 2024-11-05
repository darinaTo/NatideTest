package com.example.natifetest.data.services.remote

import com.example.natifetest.domain.entities.networkEntities.GifResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApiService {
    @GET("gifs/trending")
        suspend fun getTrendingGifs(
            @Query("limit") limit: Int = 25,
            @Query("offset") offset: Int = 0,
            @Query("rating") rating: String = "g",
            @Query("lang") lang: String = "en"
        ): Response<GifResponse>
}
