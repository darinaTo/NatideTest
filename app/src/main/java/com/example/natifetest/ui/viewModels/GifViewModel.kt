package com.example.natifetest.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.natifetest.domain.entities.uiEntities.GifUi
import com.example.natifetest.domain.usecases.DeleteGifUseCase
import com.example.natifetest.domain.usecases.FetchAndSaveGifsUseCase
import com.example.natifetest.ui.uiState.GifUiState
import com.example.natifetest.util.GifMapper.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    private val fetchAndSaveGifsUseCase: FetchAndSaveGifsUseCase,
    private val deleteGifUseCase: DeleteGifUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GifUiState())
    val uiState: StateFlow<GifUiState> = _uiState.asStateFlow()

    val searchResults: StateFlow<List<GifUi>> = _uiState
        .map { uiState ->
            var filteredGifs = uiState.gifs

            if (uiState.searchQuery.isNotEmpty()) {
                filteredGifs = filteredGifs.filter { gif ->
                    gif.title.contains(uiState.searchQuery, ignoreCase = true)
                }
            }

            filteredGifs.sortedWith(compareByDescending { gif ->
                gif.title.startsWith(uiState.searchQuery, ignoreCase = true)
            })
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
    init {
        fetchTrendingGifs()
    }


    private fun fetchTrendingGifs() {
        viewModelScope.launch {
            fetchAndSaveGifsUseCase.execute().onEach { data ->
                _uiState.update { it.copy(gifs = data, isLoading = true) }
            }.launchIn(viewModelScope)
        }
    }

    fun deleteGif(gifEntity: GifUi) {
        viewModelScope.launch {
           deleteGifUseCase.invoke(gifEntity.toEntity())
        }
    }
    fun searchGifs(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }
}
