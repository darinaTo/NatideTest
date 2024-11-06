package com.example.natifetest.ui.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.natifetest.ui.activity.component.ProgressBar
import com.example.natifetest.ui.activity.component.SearchBarScreen
import com.example.natifetest.ui.viewModels.GifViewModel

@Composable
fun GifScreen(
    viewModel: GifViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            SearchBarScreen(
                hint = "Search git",
                onTextChange = { query -> viewModel.searchGifs(query) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                LazyColumn {
                    itemsIndexed(searchResults) { index, gif ->
                        GifItem(
                            gif,
                            onItemClick = { onItemClick(index) },
                            onDeleteClick = { gifId -> viewModel.deleteGif(gifId) })
                    }
                }
            }
        } else {
            ProgressBar()
        }
    }
}
