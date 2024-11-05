package com.example.natifetest.domain.usecases

import com.example.natifetest.domain.entities.uiEntities.GifUi
import com.example.natifetest.domain.repo.GifRepository
import com.example.natifetest.util.GifMapper.toDbEntities
import com.example.natifetest.util.GifMapper.toUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchAndSaveGifsUseCase @Inject constructor(private val repository: GifRepository) {
    fun execute(): Flow<List<GifUi>> = flow {
        val gifEntities = repository.getTrendingGifs().toDbEntities()
        repository.saveGifs(gifEntities)
        emitAll(
            repository.getAllGifs().map { entities -> entities.map { it.toUIModel() } }
        )
    }
}
