package com.example.natifetest.data.services.local

import com.example.natifetest.domain.entities.dbEntities.GifEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
            gifDao.getGifs().map { gifs ->
                gifs.filter { !it.isDeleted }
            }
        }

    suspend fun deleteGif(gifId: String) =
        withContext(Dispatchers.IO) {
            gifDao.deleteGif(gifId)
        }

    suspend fun saveGifs(gifs: List<GifEntity>) {
        withContext(Dispatchers.IO) {
            gifDao.insertsGif(gifs)
        }
    }

    suspend fun getDeletedGifIds(): Flow<List<String>> =
        withContext(Dispatchers.IO) {
            gifDao.getDeletedGifsId()
        }
    }

