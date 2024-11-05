package com.example.natifetest.data.services.local

import com.example.natifetest.domain.entities.dbEntities.GifEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GifLocalDataSource @Inject constructor(
    private val gifDao: GifDao
) {
    suspend fun getGifById(id: String): Flow<GifEntity> =
        withContext(Dispatchers.IO) {
            gifDao.getGifById(id)
        }

    suspend fun getAllGifs(): Flow<List<GifEntity>> =
        withContext(Dispatchers.IO) {
            gifDao.getGifs()
        }

    suspend fun deleteGif(gif: GifEntity) =
        withContext(Dispatchers.IO) {
            gifDao.deleteGif(gif)
        }

    suspend fun saveGifs(gifs: List<GifEntity>) {
        withContext(Dispatchers.IO) {
            gifDao.insertsGif(gifs)
        }
    }
}
