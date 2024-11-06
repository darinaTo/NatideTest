package com.example.natifetest.ui.uiState

import com.example.natifetest.domain.entities.uiEntities.GifUi

data class GifDetailUiState(
    val gifList: List<GifUi> = emptyList(),
    val initialGifPosition: Int = -1,
    val isLoading : Boolean = false
)
