package com.example.natifetest.ui.uiState

import com.example.natifetest.domain.entities.uiEntities.GifUi

data class GifUiState(
    val gifs: List<GifUi> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)