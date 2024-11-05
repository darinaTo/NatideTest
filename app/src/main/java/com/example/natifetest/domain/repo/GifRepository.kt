package com.example.natifetest.domain.repo

import com.example.natifetest.domain.entities.dbEntities.GifEntity
import com.example.natifetest.domain.entities.networkEntities.GifResponse
import kotlinx.coroutines.flow.Flow


interface GifRepository {
    suspend fun getTrendingGifs(): List<GifResponse>
    suspend fun saveGifs(gifs: List<GifEntity>)
    suspend fun getGifById(id: String): Flow<GifEntity>
}
