package com.example.natifetest.ui.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.natifetest.ui.viewModels.GifViewModel

@Composable
fun GifScreen(viewModel: GifViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        SearchBarScreen(
            hint = "Search git",
            onTextChange = { query -> viewModel.searchGifs(query) })
        if (uiState.isLoading) {
            LazyColumn {
                items(searchResults) { gif ->
                    GifItem(gif, onItemClick = {})
                }
            }
        } else {
            CircularProgressIndicator()
        }
    }
}

