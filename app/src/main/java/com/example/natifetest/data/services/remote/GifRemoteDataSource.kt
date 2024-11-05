package com.example.natifetest.data.services.remote

import com.example.natifetest.domain.entities.networkEntities.GifResponse
import java.io.IOException
import javax.inject.Inject

class GifRemoteDataSource @Inject constructor(
    private val gifApiService: GifApiService
) {
    suspend fun getGifFromApi(): Result<List<GifResponse>> {
        return runCatching {
            val response = gifApiService.getTrendingGifs()

            if (response.isSuccessful) {
                response.body() ?: throw IOException("No data received.")
            } else {
                throw IOException("Error fetching data: ${response.code()} ${response.message()}")
            }
        }.onFailure { throwable ->
            throw IOException(throwable.message)        }
    }

}