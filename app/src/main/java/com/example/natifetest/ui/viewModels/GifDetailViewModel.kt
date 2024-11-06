package com.example.natifetest.ui.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.natifetest.domain.usecases.ShowGifUseCase
import com.example.natifetest.ui.uiState.GifDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifDetailViewModel @Inject constructor(
    private val showGifUseCase: ShowGifUseCase,
     savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var gifId: Int
    private val _uiState = MutableStateFlow(GifDetailUiState())
    val uiState: StateFlow<GifDetailUiState> = _uiState

    init {
        gifId = requireNotNull(savedStateHandle.get<Int>("index"))
        _uiState.update { it.copy(initialGifPosition = gifId) }
        loadGifList()
    }

    private fun loadGifList() {
        viewModelScope.launch {
            showGifUseCase.getGifList().onEach {data ->
                _uiState.update { it.copy(gifList = data, isLoading = true)}
            }.launchIn(viewModelScope)
        }
    }
}
