package com.example.natifetest.data.impl

import com.example.natifetest.data.services.local.GifLocalDataSource
import com.example.natifetest.data.services.remote.GifRemoteDataSource
import com.example.natifetest.domain.entities.dbEntities.GifEntity
import com.example.natifetest.domain.entities.networkEntities.GifResponse
import com.example.natifetest.domain.repo.GifRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GifRepositoryImpl @Inject constructor(
    private val remoteDataSource: GifRemoteDataSource,
    private val localDataSource: GifLocalDataSource
) : GifRepository {

    override suspend fun getTrendingGifs(): GifResponse {
        val deletedGifIds = localDataSource.getDeletedGifIds().first()
        val response =  remoteDataSource.getGifFromApi().getOrDefault(GifResponse(emptyList())).data
            .filter{ gif ->
                !deletedGifIds.contains(gif.id) }

        return GifResponse(response)
    }


    override suspend fun saveGifs(gifs: List<GifEntity>) {
        localDataSource.saveGifs(gifs)
    }

    override suspend fun getGifById(id: String): Flow<GifEntity> {
        return localDataSource.getGifById(id)
    }

    override suspend fun getAllGifs(): Flow<List<GifEntity>> =
        localDataSource.getAllGifs()


    override suspend fun deleteGif(gifId: String) {
        localDataSource.deleteGif(gifId)
    }
}
