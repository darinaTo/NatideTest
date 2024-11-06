package com.example.natifetest.domain.usecases

import com.example.natifetest.domain.entities.uiEntities.GifUi
import com.example.natifetest.domain.repo.GifRepository
import com.example.natifetest.util.GifMapper.toUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShowGifUseCase @Inject constructor(
    private val gifRepository: GifRepository
) {
    suspend fun getGifList() : Flow<List<GifUi>> {
        return gifRepository.getAllGifs().map { gifEntities -> gifEntities.map { it.toUIModel() } }
    }
}