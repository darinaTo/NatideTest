package com.example.natifetest.data.impl

import com.example.natifetest.data.services.local.GifLocalDataSource
import com.example.natifetest.data.services.remote.GifRemoteDataSource
import com.example.natifetest.domain.entities.dbEntities.GifEntity
import com.example.natifetest.domain.entities.networkEntities.GifResponse
import com.example.natifetest.domain.repo.GifRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifRepositoryImpl @Inject constructor(
    private val remoteDataSource: GifRemoteDataSource,
    private val localDataSource: GifLocalDataSource
) : GifRepository {

    override suspend fun getTrendingGifs(): List<GifResponse> {
        return remoteDataSource.getGifFromApi().getOrDefault(emptyList())
    }


    override suspend fun saveGifs(gifs: List<GifEntity>) {
        localDataSource.saveGifs(gifs)
    }

    override suspend fun getGifById(id: String): Flow<GifEntity> {
        return localDataSource.getGifById(id)
    }
}