package com.example.natifetest.ui.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.decode.ImageDecoderDecoder
import com.example.natifetest.ui.activity.component.ProgressBar
import com.example.natifetest.ui.viewModels.GifDetailViewModel

@Composable
fun GifDetailScreen(
    viewModel: GifDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val pagerState = rememberPagerState(initialPage = uiState.initialGifPosition,
        initialPageOffsetFraction = 0f,
        pageCount = {uiState.gifList.size})

    LaunchedEffect(uiState.initialGifPosition) {
        pagerState.scrollToPage(uiState.initialGifPosition)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (uiState.isLoading) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                GifItemDetail(gif = uiState.gifList[page].imageUrl)
            }
        } else {
            ProgressBar()
        }
    }
}

@Composable
fun GifItemDetail(
    gif: String
) {
    var isLoading by remember { mutableStateOf(true) }

    val gifEnabledLoader = ImageLoader.Builder(LocalContext.current).components {
        add(ImageDecoderDecoder.Factory())
    }.build()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = gif,
            contentDescription = "gif",
            imageLoader = gifEnabledLoader,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize(),
            onState = { state ->
                isLoading = state is AsyncImagePainter.State.Loading
            }
        )
        if (isLoading) {
            ProgressBar()
        }
    }
}



